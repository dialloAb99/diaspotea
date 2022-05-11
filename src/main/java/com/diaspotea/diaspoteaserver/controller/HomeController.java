package com.diaspotea.diaspoteaserver.controller;

import com.diaspotea.diaspoteaserver.dto.MenuDto;
import com.diaspotea.diaspoteaserver.dto.ProduitDTO;
import com.diaspotea.diaspoteaserver.models.Categorie;
import com.diaspotea.diaspoteaserver.models.Menu;
import com.diaspotea.diaspoteaserver.models.Produit;
import com.diaspotea.diaspoteaserver.models.Utilisateur;
import com.diaspotea.diaspoteaserver.services.CategorieService;
import com.diaspotea.diaspoteaserver.services.MenuService;
import com.diaspotea.diaspoteaserver.services.ProduitService;
import com.diaspotea.diaspoteaserver.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

public class HomeController {
    private CategorieService categorieService;
    private final ProduitService produitService;
    private MenuService menuService;
    private UtilisateurService utilisateurService;
    @Autowired
    public HomeController(CategorieService categorieService, ProduitService produitService, MenuService menuService, UtilisateurService utilisateurService){
        this.categorieService = categorieService;
        this.produitService=produitService;
        this.menuService=menuService;
        this.utilisateurService=utilisateurService;

    }
    //l'url de la page d'accueil
    @GetMapping("/")
    //creation d'une methode
    public String index(Model model){
        //ajouter un attribut au model
        model.addAttribute("produits",produitService.recupereToutProduits());
        return "index";
    }
    @GetMapping("/boisson")
    public String boissons(Model model){
        List<Produit>produits;
        Categorie categorie=categorieService.getCategorie(1);
        produits=produitService.recupererProduitParCategorie(categorie);
        model.addAttribute("boissons",produits);
        if (!model.containsAttribute("produitDTO")) {
            model.addAttribute("produitDTO", new ProduitDTO());
        }
        return "boissons";
    }
    @GetMapping("/dessert")
    //j'ai dis que j'aurais besoin d'un mobel pour afficher les dessert
    public String desserts(Model model){
//        j'ai declarer une liste de produit
        List<Produit>desserts;
        Categorie categorie=categorieService.getCategorie(2);
//        j'ai affecté les produits qui ont la categorie desserts à la liste de dessert'
        desserts=produitService.recupererProduitParCategorie(categorie);
//        j'ai ajouter un attribut dessert qui porte comme valeur la liste de dessert'
        model.addAttribute("desserts",desserts);
        if (!model.containsAttribute("produitDTO")) {
            model.addAttribute("produitDTO", new ProduitDTO());
        }
        return "desserts";
    }
    @GetMapping("/petit-dejeuner")
    public String petitDejeuners(Model model){
        List<Menu>petitDejeuners =menuService.recupereToutMenu();
        model.addAttribute("petitDejeuners",petitDejeuners);
        if (!model.containsAttribute("menuDto")) {
            model.addAttribute("menuDto", new MenuDto());
        }
       return "petit-dejeuners";
    }
    @GetMapping("/moncompte")
    public String monCompte(Model model){
        Utilisateur utilisateur=utilisateurService.recupereUtilisateur(1);
        model.addAttribute("monCompte",utilisateur);
        return "monCompte";
    }

}
