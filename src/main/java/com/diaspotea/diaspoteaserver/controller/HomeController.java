package com.diaspotea.diaspoteaserver.controller;

import com.diaspotea.diaspoteaserver.Autorites;
import com.diaspotea.diaspoteaserver.Categories;
import com.diaspotea.diaspoteaserver.dto.MenuPanierDto;
import com.diaspotea.diaspoteaserver.dto.ProduitPanierDTO;
import com.diaspotea.diaspoteaserver.dto.UtilisateurInscriptionDto;
import com.diaspotea.diaspoteaserver.models.Menu;
import com.diaspotea.diaspoteaserver.models.Produit;
import com.diaspotea.diaspoteaserver.models.UserPrincipal;
import com.diaspotea.diaspoteaserver.services.CategorieService;
import com.diaspotea.diaspoteaserver.services.MenuService;
import com.diaspotea.diaspoteaserver.services.ProduitService;
import com.diaspotea.diaspoteaserver.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller

public class HomeController {
    private final CategorieService categorieService;
    private final ProduitService produitService;
    private final MenuService menuService;
    private final UtilisateurService utilisateurService;
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
    public String index(Model model, UserPrincipal principal){
        //ajouter un attribut au model
        model.addAttribute("produits",produitService.recupereToutProduits());
        return "index";
    }
    @GetMapping("/boisson")
    public String boissons(Model model){
        List<Produit>produits;
        produits=produitService.recupererProduitParCategorie(Categories.BOISSON.toString().toLowerCase());
        model.addAttribute("boissons",produits);
        if (!model.containsAttribute("produitPanierDTO")) {
            model.addAttribute("produitPanierDTO", new ProduitPanierDTO());
        }
        return "boissons";
    }
    @GetMapping("/dessert")
    //j'ai dis que j'aurais besoin d'un mobel pour afficher les dessert
    public String desserts(Model model){
//        j'ai declarer une liste de produit
        List<Produit>desserts;
//        j'ai affecté les produits qui ont la categorie desserts à la liste de dessert'
        desserts=produitService.recupererProduitParCategorie(Categories.DESSERT.toString().toLowerCase());
//        j'ai ajouter un attribut dessert qui porte comme valeur la liste de dessert'
        model.addAttribute("desserts",desserts);
        if (!model.containsAttribute("produitPanierDTO")) {
            model.addAttribute("produitPanierDTO", new ProduitPanierDTO());
        }
        return "desserts";
    }
    @GetMapping("/petit-dejeuner")
    public String petitDejeuners(Model model){
        List<Menu>petitDejeuners =menuService.recupereToutMenu();
        model.addAttribute("petitDejeuners",petitDejeuners);
        if (!model.containsAttribute("menuPanierDto")) {
            model.addAttribute("menuPanierDto", new MenuPanierDto());
        }
       return "petit-dejeuners";
    }
    @GetMapping("/moncompte")
    public String monCompte(Model model, Authentication authentication){
        UserPrincipal currentUser=(UserPrincipal)  authentication.getPrincipal();
        model.addAttribute("userId",currentUser.getId());
        return "monCompte";
    }
    @GetMapping("/inscription")
    public String inscription(UtilisateurInscriptionDto utilisateurInscriptionDto){
        return "formInscription";
    }
    @PostMapping("/inscription")
    public String inscription(@Valid UtilisateurInscriptionDto utilisateurInscriptionDto, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
                return  "formInscription";
        }
        if(!utilisateurService.exist(utilisateurInscriptionDto.getEmail())) {
            utilisateurService.ajouterUtilisateur(utilisateurInscriptionDto, Autorites.CLIENT.toString());
        }else {
            bindingResult.addError(new FieldError("utilisateurInscriptionDto","email","un compte existe déja avec ce email"));
            return  "formInscription";
        }
        return "redirect:/";
    }


}
