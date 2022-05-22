package com.diaspotea.diaspoteaserver.controller;


import com.diaspotea.diaspoteaserver.dto.*;
import com.diaspotea.diaspoteaserver.models.*;
import com.diaspotea.diaspoteaserver.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@Controller

public class PanierController {
    private final PanierService panierService;
    private final ProduitService produitService;
    private final ClientService clientService;
    private final ProduitTarifService produitTarifService;
    private final LigneDecommandeService ligneDecommandeService;
    private final UtilisateurService utilisateurService;
    private final MenuService menuService;

    @Autowired
    public PanierController(PanierService panierService, ProduitService produitService, ClientService clientService, ProduitTarifService produitTarifService, LigneDecommandeService ligneDecommandeService, UtilisateurService utilisateurService, MenuService menuService) {
        this.panierService = panierService;
        this.produitService = produitService;
        this.clientService = clientService;
        this.produitTarifService = produitTarifService;
        this.ligneDecommandeService = ligneDecommandeService;
        this.utilisateurService = utilisateurService;
        this.menuService = menuService;
    }


    @PostMapping(value = "/panier/ajouter/produit")
    public String panierAjouter(@Valid @ModelAttribute ProduitDTO produitDTO, HttpServletRequest request, BindingResult bindingResult, RedirectAttributes attr) {
        URL url = null;
        try {
            url = new URL(request.getHeader("referer"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        if (bindingResult.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.produitDTO", bindingResult);
            attr.addFlashAttribute("produitDTO", produitDTO);
            return "redirect:/boisson";
        }
        Client client = clientService.recupereClient(1);
        boolean panierEstActif = panierService.panierEstActif(client);
        LigneDeCommandeProduit ligneDeCommande = new LigneDeCommandeProduit();
        Produit produit = produitService.recupererProduit(produitDTO.getId());
        Panier panier = null;
        if (panierEstActif) {
            panier = panierService.recuperePanierActif(client);
            if (panier.ligneDeCommandeProduitExiste(produitDTO.getId(), produitDTO.getTailleID())) {
                ligneDeCommande = panier.recupererLigneDeCommandeProduit(produitDTO.getId(), produitDTO.getTailleID());
                int nouvellleQuantite = ligneDeCommande.getQuantiter() + produitDTO.getQuantiter();
                ligneDeCommande.setQuantiter(nouvellleQuantite);
            }

        } else {
            panier = new Panier();
            panier.setClient(client);
            panier.setEtatPanier(true);

        }
        ligneDeCommande.setProduit(produit);
        ProduitTarif produitTarif = produitTarifService.recupereProduitTarif(new ProduitTarifID(produitDTO.getId(), produitDTO.getTailleID()));
        ligneDeCommande.setTaille(produitTarif.getTaille());
        if(ligneDeCommande.getQuantiter()==0){
            ligneDeCommande.setQuantiter(produitDTO.getQuantiter());
        }
        ligneDeCommande.setPrix(produitTarif.getTarif());

        ligneDeCommande.setPanier(panier);
        panier.ajouterLigneDeCommande(ligneDeCommande);

        panierService.ajouterPanier(panier);
        //ajoute ligne de commmande au panier


        return "redirect:" + url.getPath();
    }

    @PostMapping(value = "/panier/ajouter/menu")
    public String ajouterMenuPanier(@Valid @ModelAttribute MenuDto menuDto, BindingResult bindingResult, RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.menuDto", bindingResult);
            attr.addFlashAttribute("menuDto", menuDto);
            return "redirect:/petit-dejeuner";
        }
        LigneDeCommandeMenu ligneDeCommande = new LigneDeCommandeMenu();
        Client client = clientService.recupereClient(1);
        Panier panier = null;
        boolean panierEstActif = panierService.panierEstActif(client);
        Menu menu = menuService.recupereMenu(menuDto.getMenuId());
        if (panierEstActif) {
            panier = panierService.recuperePanierActif(client);
            if (panier.ligneDeCommandeMenuExiste(menuDto.getMenuId())) {
                ligneDeCommande = panier.recupererLigneDeCommandeMenu(menuDto.getMenuId());
            }
            int nouvellleQuantite = ligneDeCommande.getQuantiter() + menuDto.getQuantiter();
            ligneDeCommande.setQuantiter(nouvellleQuantite);
        }else{
            panier =new Panier();
            ligneDeCommande.setQuantiter(menuDto.getQuantiter());
            panier.setClient(client);
            panier.setEtatPanier(true);
        }
        ligneDeCommande.setMenu(menu);
        ligneDeCommande.setPanier(panier);
        ligneDeCommande.setPrix(menu.getPrix());
        panier.ajouterLigneDeCommande(ligneDeCommande);
        panierService.ajouterPanier(panier);
        return "redirect:/petit-dejeuner";
    }

    @PutMapping(value = "/panier/diminuer/{ligneDeCommandeId}")
    public String diminuerQuantiter(@PathVariable(value = "ligneDeCommandeId") Integer ligneDeCommandeId) {
        LigneDeCommande ligneDeCommande = ligneDecommandeService.recupereLigneDeCommande(ligneDeCommandeId);
        ligneDeCommande.diminueQuantiter();
        ligneDecommandeService.modifierLigneDeCommande(ligneDeCommande);
        return "redirect:/monpanier/" + ligneDeCommande.getPanier().getClient().getId();
    }

    @PutMapping(value = "/panier/augmenter/{ligneDeCommandeId}")
    public String augmenterQuantite(@PathVariable(value = "ligneDeCommandeId") Integer ligneDeCommandeId) {
        LigneDeCommande ligneDeCommande = ligneDecommandeService.recupereLigneDeCommande(ligneDeCommandeId);
        ligneDeCommande.augmenteQuantiter();
        ligneDecommandeService.modifierLigneDeCommande(ligneDeCommande);
        return "redirect:/monpanier/" + ligneDeCommande.getPanier().getClient().getId();
    }

    @PutMapping("/panier/modify")
    public String modifierPanier(@RequestBody ProduitDTO produitDTO) {
        Panier panier = panierService.recuperePanier(produitDTO.getPanierId());
        panierService.ajouterPanier(panier);
        //recuperer le panier
        //recuperer la ligne de commande
        // modifier ligne de commande
        // sauvegarder dans la base de données la ligne de commande
        // sauvegarder dans la base de données le panier
        return "redirect:/monpanier/" + panier.getClient().getId();
    }

    @GetMapping("/monpanier/{id}")
    public String monPanier(Model model, @PathVariable("id") Integer idUtilisateur) {
        Utilisateur utilisateur = utilisateurService.recupereUtilisateur(idUtilisateur);
        Client client = utilisateur.getClient();
        if (Objects.isNull(client)) {
            return "panier";
        }
        boolean panierEstActif = panierService.panierEstActif(client.getId());
        if (panierEstActif) {
            Panier panier = panierService.recuperePanierActif(client.getId());
            List<LigneDeCommande> ligneDeCommandeDtos = panier.getLigneDeCommandes();
            PanierDto panierDto = new PanierDto(panier.getId(), ligneDeCommandeDtos, panier.getClient());
            // creer le panierdto  a partir panier
            model.addAttribute("panier", panierDto);
        } else {
            model.addAttribute("panier", null);
        }
        model.addAttribute("client", utilisateur.getClient());
        return "panier";
    }

    @PutMapping("/supprimerLigneDeCommande/{ligneDeCommandeId}")
    @ResponseBody
    public void suprrimerLigneDeCommandePanier(@PathVariable Integer ligneDeCommandeId) {
        ligneDecommandeService.deleteLigneDeCommande(ligneDeCommandeId);
    }

    @GetMapping("/modifier-adresse-de-livraison/{clientId}")
    public String recupereAdresseDeLivraison(ModiferFormAdresseLivraisonDto modiferFormAdresseLivraisonDto, Model model) {
        Client client = clientService.recupereClient(modiferFormAdresseLivraisonDto.getClientId());
        modiferFormAdresseLivraisonDto.setAdresse(client.getAdresse());
        modiferFormAdresseLivraisonDto.setCodePostale(client.getCodePostale());
        modiferFormAdresseLivraisonDto.setVille(client.getVille());
        modiferFormAdresseLivraisonDto.setEtage(String.valueOf(client.getEtage()));
        modiferFormAdresseLivraisonDto.setClientId(client.getId());
        modiferFormAdresseLivraisonDto.setMemeAdresse(false);
        model.addAttribute("modiferFormAdresseLivraisonDto", modiferFormAdresseLivraisonDto);
        return "formulaireAdresseLivraison";
    }

    @PostMapping("/modifier-adresse-de-livraison/{clientId}")
    public String modifierAdresseLivraison(@Valid @ModelAttribute ModiferFormAdresseLivraisonDto modiferFormAdresseLivraisonDto, BindingResult bindingResult) {
        Client client = clientService.recupereClient(modiferFormAdresseLivraisonDto.getClientId());
        if (bindingResult.hasErrors()) {
            return "formulaireAdresseLivraison";
        }
        client.setAdresse(modiferFormAdresseLivraisonDto.getAdresse());
        client.setCodePostale(modiferFormAdresseLivraisonDto.getCodePostale());
        client.setVille(modiferFormAdresseLivraisonDto.getVille());
        client.setEtage(modiferFormAdresseLivraisonDto.getEtage());
        clientService.modifierClient(client);
        return "redirect:/monpanier/" + client.getUtilisateur().getId();
    }

}
