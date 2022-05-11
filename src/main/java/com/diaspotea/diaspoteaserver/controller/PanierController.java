package com.diaspotea.diaspoteaserver.controller;


import com.diaspotea.diaspoteaserver.dto.MenuDto;
import com.diaspotea.diaspoteaserver.dto.PanierDto;
import com.diaspotea.diaspoteaserver.dto.ProduitDTO;
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
        Panier panier=null;
        if (panierEstActif) {
             panier = panierService.recuperePanierActif(client);
                Produit produit = produitService.recupererProduit(produitDTO.getId());
                ProduitTarif produitTarif = produitTarifService.recupereProduitTarif(new ProduitTarifID(produitDTO.getId(), produitDTO.getTailleID()));
                if (panier.ligneDeCommandeProduitExiste(produitDTO.getId(), produitDTO.getTailleID())) {
                    ligneDeCommande = panier.recupererLigneDeCommandeProduit(produitDTO.getId(), produitDTO.getTailleID());
                } else {
                    ligneDeCommande.setProduit(produit);
                    ligneDeCommande.setTaille(produitTarif.getTaille());
                }
                ligneDeCommande.setPrix(produitTarif.getTarif());
            }

            ligneDeCommande.setQuantiter(produitDTO.getQuantiter());
            ligneDeCommande.setPanier(panier);
            int nouvellleQuantite = ligneDeCommande.getQuantiter() + produitDTO.getQuantiter();
            ligneDecommandeService.AjouterLigneDeCommande(ligneDeCommande);
            panier.ajouterLigneDeCommande(ligneDeCommande);
            panierService.ajouterPanier(panier);
            //ajoute ligne de commmande au panier


        return "redirect:" + url.getPath();
    }
    @PostMapping(value = "/panier/ajouter/menu")
    public String ajouterMenuPanier(@Valid @ModelAttribute  MenuDto menuDto, BindingResult bindingResult,RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.menuDto", bindingResult);
            attr.addFlashAttribute("menuDto", menuDto);
            return "redirect:/petit-dejeuner";
        }
        LigneDeCommandeMenu ligneDeCommande = new LigneDeCommandeMenu();
        Client client = clientService.recupereClient(1);
        Panier panier=null;
        boolean panierEstActif = panierService.panierEstActif(client);
        if (panierEstActif) {
            panier = panierService.recuperePanierActif(client);
            menuDto.getMenuId();
            if (panier.ligneDeCommandeMenuExiste(menuDto.getMenuId())) {
                ligneDeCommande = panier.recupererLigneDeCommandeMenu(menuDto.getMenuId());
            }
            Menu menu = menuService.recupereMenu(menuDto.getMenuId());
            ligneDeCommande.setMenu(menu);
            ligneDeCommande.setPrix(menu.getPrix());
        }
        ligneDeCommande.setQuantiter(menuDto.getQuantiter());
        ligneDeCommande.setPanier(panier);
        int nouvellleQuantite = ligneDeCommande.getQuantiter() + menuDto.getQuantiter();
        ligneDecommandeService.AjouterLigneDeCommande(ligneDeCommande);
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
}
