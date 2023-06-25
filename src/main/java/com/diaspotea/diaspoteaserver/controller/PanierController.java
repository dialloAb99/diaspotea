package com.diaspotea.diaspoteaserver.controller;


import com.diaspotea.diaspoteaserver.Categories;
import com.diaspotea.diaspoteaserver.dto.MenuPanierDto;
import com.diaspotea.diaspoteaserver.dto.PanierDto;
import com.diaspotea.diaspoteaserver.dto.ProduitPanierDTO;
import com.diaspotea.diaspoteaserver.models.Categorie;
import com.diaspotea.diaspoteaserver.models.Client;
import com.diaspotea.diaspoteaserver.models.LigneDeCommande;
import com.diaspotea.diaspoteaserver.models.LigneDeCommandeMenu;
import com.diaspotea.diaspoteaserver.models.LigneDeCommandeProduit;
import com.diaspotea.diaspoteaserver.models.Menu;
import com.diaspotea.diaspoteaserver.models.Panier;
import com.diaspotea.diaspoteaserver.models.Produit;
import com.diaspotea.diaspoteaserver.models.ProduitTarif;
import com.diaspotea.diaspoteaserver.models.ProduitTarifID;
import com.diaspotea.diaspoteaserver.models.UserPrincipal;
import com.diaspotea.diaspoteaserver.models.Utilisateur;
import com.diaspotea.diaspoteaserver.services.LigneDecommandeService;
import com.diaspotea.diaspoteaserver.services.MenuService;
import com.diaspotea.diaspoteaserver.services.PanierService;
import com.diaspotea.diaspoteaserver.services.ProduitService;
import com.diaspotea.diaspoteaserver.services.ProduitTarifService;
import com.diaspotea.diaspoteaserver.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
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
    private final ProduitTarifService produitTarifService;
    private final LigneDecommandeService ligneDecommandeService;
    private final UtilisateurService utilisateurService;
    private final MenuService menuService;

    @Autowired
    public PanierController(PanierService panierService, ProduitService produitService, ProduitTarifService produitTarifService, LigneDecommandeService ligneDecommandeService, UtilisateurService utilisateurService, MenuService menuService) {
        this.panierService = panierService;
        this.produitService = produitService;
        this.produitTarifService = produitTarifService;
        this.ligneDecommandeService = ligneDecommandeService;
        this.utilisateurService = utilisateurService;
        this.menuService = menuService;
    }


    @PostMapping(value = "/panier/ajouter/produit")
    @PreAuthorize("hasAuthority('client')")
    public String panierAjouter(@Valid @ModelAttribute ProduitPanierDTO produitPanierDTO,  BindingResult bindingResult,HttpServletRequest request,  Authentication authentication, RedirectAttributes attr) {
        URL url = null;
        UserPrincipal currentUser=(UserPrincipal)  authentication.getPrincipal();
        try {
            url = new URL(request.getHeader("referer"));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        if (bindingResult.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.dto", bindingResult);
            attr.addFlashAttribute("dto", produitPanierDTO);
            Produit produit =produitService.recupererProduit(produitPanierDTO.getId());
            Categorie categorie= produit.getCategorie();
            if(categorie.getNom().contains(Categories.BOISSON.toString().toLowerCase())){
                return "redirect:/boisson";
            }
           else if (categorie.getNom().contains(Categories.DESSERT.toString().toLowerCase())){
                return "redirect:/dessert";
            }
           else{
               return "redirect:/";
            }

        }
        Client client = utilisateurService.recuperUtilisateurParType(currentUser.getId(),Client.class);
        boolean panierEstActif = panierService.panierEstActif(client);
        LigneDeCommandeProduit ligneDeCommande = new LigneDeCommandeProduit();
        Produit produit = produitService.recupererProduit(produitPanierDTO.getId());
        Panier panier = null;
        if (panierEstActif) {
            panier = panierService.recuperePanierActif(client);
            if (panier.ligneDeCommandeProduitExiste(produitPanierDTO.getId(), produitPanierDTO.getTailleID())) {
                ligneDeCommande = panier.recupererLigneDeCommandeProduit(produitPanierDTO.getId(), produitPanierDTO.getTailleID());
                int nouvellleQuantite = ligneDeCommande.getQuantiter() + produitPanierDTO.getQuantiter();
                ligneDeCommande.setQuantiter(nouvellleQuantite);
            }

        } else {
            panier = new Panier();
            panier.setClient(client);
            panier.setEtatPanier(true);

        }
        ligneDeCommande.setProduit(produit);
        ProduitTarif produitTarif = produitTarifService.recupereProduitTarif(new ProduitTarifID(produitPanierDTO.getId(), produitPanierDTO.getTailleID()));
        ligneDeCommande.setTaille(produitTarif.getTaille());
        if(ligneDeCommande.getQuantiter()==0){
            ligneDeCommande.setQuantiter(produitPanierDTO.getQuantiter());
        }
        ligneDeCommande.setPrix(produitTarif.getPrix());

        ligneDeCommande.setPanier(panier);
        panier.ajouterLigneDeCommande(ligneDeCommande);

        panierService.ajouterPanier(panier);
        //ajoute ligne de commmande au panier


        return "redirect:" + url.getPath();
    }

    @PostMapping(value = "/panier/ajouter/menu")
    @PreAuthorize("hasAuthority('client')")
    public String ajouterMenuPanier(@Valid MenuPanierDto menuPanierDto, BindingResult bindingResult, RedirectAttributes attr, Authentication authentication ) {
        UserPrincipal currentUser=(UserPrincipal)  authentication.getPrincipal();
        if(currentUser==null){
            throw new RuntimeException("Incorrect session");
        }
        if (bindingResult.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.dto", bindingResult);
            attr.addFlashAttribute("dto", menuPanierDto);
            return "redirect:/petit-dejeuner";
        }
        LigneDeCommandeMenu ligneDeCommande = new LigneDeCommandeMenu();
        Client client = utilisateurService.recuperUtilisateurParType(currentUser.getId(), Client.class);
        Panier panier = null;
        boolean panierEstActif = panierService.panierEstActif(client);
        Menu menu = menuService.recupereMenu(menuPanierDto.getMenuId());
        if (panierEstActif) {
            panier = panierService.recuperePanierActif(client);
            if (panier.ligneDeCommandeMenuExiste(menuPanierDto.getMenuId())) {
                ligneDeCommande = panier.recupererLigneDeCommandeMenu(menuPanierDto.getMenuId());
            }
            int nouvellleQuantite = ligneDeCommande.getQuantiter() + menuPanierDto.getQuantiter();
            ligneDeCommande.setQuantiter(nouvellleQuantite);
        }else{
            panier =new Panier();
            ligneDeCommande.setQuantiter(menuPanierDto.getQuantiter());
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
    @PreAuthorize("hasAuthority('client')")
    public String diminuerQuantiter(@PathVariable(value = "ligneDeCommandeId") Integer ligneDeCommandeId) {
        LigneDeCommande ligneDeCommande = ligneDecommandeService.recupereLigneDeCommande(ligneDeCommandeId);
        ligneDeCommande.diminueQuantiter();
        ligneDecommandeService.modifierLigneDeCommande(ligneDeCommande);
        return "redirect:/monpanier/" + ligneDeCommande.getPanier().getClient().getId();
    }

    @PutMapping(value = "/panier/augmenter/{ligneDeCommandeId}")
    @PreAuthorize("hasAuthority('client')")
    public String augmenterQuantite(@PathVariable(value = "ligneDeCommandeId") Integer ligneDeCommandeId) {
        LigneDeCommande ligneDeCommande = ligneDecommandeService.recupereLigneDeCommande(ligneDeCommandeId);
        ligneDeCommande.augmenteQuantiter();
        ligneDecommandeService.modifierLigneDeCommande(ligneDeCommande);
        return "redirect:/monpanier/" + ligneDeCommande.getPanier().getClient().getId();
    }



    @GetMapping("/monpanier/{id}")
    @PreAuthorize("hasAuthority('client')")
    public String monPanier(Model model, @PathVariable("id") Integer idUtilisateur) {
        Utilisateur utilisateur = utilisateurService.recuperUtilisateurParType(idUtilisateur,Client.class);
        if (Objects.isNull(utilisateur)) {
            return "panier";
        }
        boolean panierEstActif = panierService.panierEstActif(utilisateur.getId());
        if (panierEstActif) {
            Panier panier = panierService.recuperePanierActif(utilisateur.getId());
            List<LigneDeCommande> ligneDeCommandeDtos = panier.getLigneDeCommandes();
            PanierDto panierDto = new PanierDto(panier.getId(), ligneDeCommandeDtos, panier.getClient());
            // creer le panierdto  a partir panier
            model.addAttribute("panier", panierDto);
        } else {
            model.addAttribute("panier", null);
        }
        model.addAttribute("client", utilisateur);
        return "panier";
    }

@DeleteMapping("/supprimerLigneDeCommande/{ligneDeCommandeId}")
    @PreAuthorize("hasAuthority('client')")
    public ModelAndView suprrimerLigneDeCommandePanier(@PathVariable Integer ligneDeCommandeId, Authentication authentication) {
        ligneDecommandeService.deleteLigneDeCommande(ligneDeCommandeId);
    UserPrincipal currentUser=(UserPrincipal)  authentication.getPrincipal();
        return new ModelAndView("redirect:/monpanier/"+currentUser.getId());
    }



}
