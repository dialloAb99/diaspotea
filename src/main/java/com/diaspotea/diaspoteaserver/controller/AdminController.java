package com.diaspotea.diaspoteaserver.controller;

import com.diaspotea.diaspoteaserver.dto.*;
import com.diaspotea.diaspoteaserver.models.*;
import com.diaspotea.diaspoteaserver.services.MenuService;
import com.diaspotea.diaspoteaserver.services.ProduitService;
import com.diaspotea.diaspoteaserver.services.ProduitTarifService;
import com.diaspotea.diaspoteaserver.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProduitService produitService;
    private final MenuService menuService;
    private final TypeService typeService;
    private final ProduitTarifService produitTarifService;

    public AdminController(ProduitService produitService, MenuService menuService, TypeService typeService, ProduitTarifService produitTarifService) {
        this.produitService = produitService;
        this.menuService = menuService;
        this.typeService = typeService;
        this.produitTarifService = produitTarifService;
    }

    @Autowired

    @GetMapping({"/", ""})
    public String home() {
        return "admin/home";
    }

    @GetMapping("/produits")
    public String voirProduits(Model model) {
        List<Produit> produits = produitService.recupereToutProduits();
        List<Object> produitDtos = new ArrayList<>();
        List<TypeProduitDto> typeProduitDtos = typeService.getAll();
        for (Menu menu : menuService.recupereToutMenu()) {
            MenuDto menuDto = new MenuDto(menu.getId(), menu.getNom(), menu.getDescription(), menu.getPrix());
            produitDtos.add(menuDto);
        }
        for (Produit produit : produits
        ) {
            List<PhotoArticleDto> photoArticleDtos = new ArrayList<>();
            for (PhotoArticle photoArticle : produit.getPhotoArticles()
            ) {
                PhotoArticleDto photoArticleDto = new PhotoArticleDto(photoArticle.getId(), photoArticle.getLibele(), photoArticle.getUrl());
                photoArticleDtos.add(photoArticleDto);

            }
            TypeProduitDto typeProduitDto = new TypeProduitDto(produit.getTypeProduit().getId(), produit.getTypeProduit().getNom());
            for (ProduitTarif produitTarif : produit.getProduitTarifs()
            ) {
                ProduitTarifDto produitTarifDto = new ProduitTarifDto(produitTarif.getProduitTarifID(), produitTarif.getPrix(), new TailleDto(produitTarif.getTaille().getId(), produitTarif.getTaille().getName()));
                ProduitDto produitDto = new ProduitDto(produit.getId(), produit.getNom(), produit.getDescription(), photoArticleDtos, typeProduitDto, typeProduitDtos, produitTarifDto);
                produitDtos.add(produitDto);
            }


        }

        model.addAttribute("produits", produitDtos);
        return "admin/listeDesProduits";
    }

    @GetMapping("/modifierProduit/{id}/{tailleId}")
    public String modifierProduit(Model model, @PathVariable int id, @PathVariable int tailleId) {
        Produit produit = produitService.recupererProduit(id);
        float tarif = 0f;

        for (ProduitTarif produitTarif : produit.getProduitTarifs()) {
            if (produitTarif.getProduitTarifID().getTailleID() == tailleId) {
                tarif = produitTarif.getPrix();

            }
        }
        List<TypeProduitDto> typeProduitDtos = typeService.getAll();
        List<PhotoArticleDto> photoArticleDtos = new ArrayList<>();
        for (PhotoArticle photoArticle : produit.getPhotoArticles()
        ) {
            PhotoArticleDto photoArticleDto = new PhotoArticleDto(photoArticle.getId(), photoArticle.getLibele(), photoArticle.getUrl());
            photoArticleDtos.add(photoArticleDto);

        }
        TypeProduitDto typeProduitDto = new TypeProduitDto(produit.getTypeProduit().getId(), produit.getTypeProduit().getNom());

        ModifierProduitDto produitDto = new ModifierProduitDto(produit.getId(), produit.getNom(), produit.getDescription(), photoArticleDtos, typeProduitDto, typeProduitDtos, tarif, tailleId);

        model.addAttribute("produit", produitDto);
        return "admin/modifierProduit";
    }

    @PostMapping("/modifierProduit/{id}/{tailleId}")
    public String produitModifier(Model model, @Valid ModifierProduitDto produitDto, @PathVariable int tailleId, RedirectAttributes attr) {
        Produit produit = produitService.recupererProduit(produitDto.getId());
        if (produit == null) {
            throw new IllegalArgumentException("Can't process data with supplied information");
        }
        ProduitTarif nouveauProduitTarif = produitTarifService.recupereProduitTarif(new ProduitTarifID(produit.getId(), tailleId));
        List<ProduitTarif> produitTarifs = produit.getProduitTarifs();
        for (int i = 0; i < produitTarifs.size(); i++) {
            if (produitTarifs.get(i).getProduitTarifID() == nouveauProduitTarif.getProduitTarifID()) {
                produitTarifs.remove(i);
                break;

            }
        }
        nouveauProduitTarif.setPrix(produitDto.getPrix());
        produitTarifs.add(nouveauProduitTarif);
        produit.setId(produitDto.getId());
        produit.setNom(produitDto.getNom());
        produit.setDescription(produitDto.getDescription());
        produitService.modifierproduit(produit);
        attr.addFlashAttribute("status", "article à etait bien mise à jour");
        return "redirect:/admin/produits";
    }

    @DeleteMapping("/produit/{id}/{tailleId}")
    public String deleteProduit(@PathVariable int id, @PathVariable int tailleId, RedirectAttributes attr) {
        Produit produit = produitService.recupererProduit(id);
        produit.removeProduitTarif(id, tailleId);
        produitService.modifierproduit(produit);
        attr.addFlashAttribute("status", "article à été bien supprimer");
        return "redirect:/admin/produits";
    }

    @DeleteMapping("/menu/{id}")
    public String deleteMenu(@PathVariable int id, RedirectAttributes attr) {
        Menu menu = menuService.recupereMenu(id);
        menuService.deleteMenu(menu);
        attr.addFlashAttribute("status", "Le menu à été bien supprimer");
        return "redirect:/admin/produits";
    }

    @GetMapping("/modifierMenu/{id}")
    public String modifierMenu(Model model, @Valid @PathVariable Integer id) {
        Menu menu = menuService.recupereMenu(id);
        List<NomProduitDto> nomProduitDtos = menu.getProduits().stream()
                .map(produit ->
                    new NomProduitDto(produit.getProduitTarifID(),produit.getProduit().getNom(),new TailleDto(produit.getTaille().getId(),produit.getTaille().getName()))
                ).collect(Collectors.toList());
        ModifierMenuDto modifierMenuDto = new ModifierMenuDto(menu.getId(), menu.getNom(), menu.getDescription(), menu.getPrix(), nomProduitDtos);
        model.addAttribute("produit", modifierMenuDto);
        return "admin/modifierProduit";
    }

    @PutMapping("/modifierMenu/{id}")
    public String modifierMenu(@Valid ModifierMenuDto menuDto, RedirectAttributes attr) {
        Menu menu = menuService.recupereMenu(menuDto.getId());
        menu.setNom(menuDto.getNom());
        menu.setDescription(menuDto.getDescription());
        menu.setPrix(menu.getPrix());
        List<Produit> produits = new ArrayList<>();
        menu.getProduits().removeIf(produit -> menuDto.getProduits()
                .stream()
                .noneMatch(produitDto -> produitDto.getProduitTarifID() == produit.getProduitTarifID()));
        menuService.modifierMenu(menu);
        attr.addFlashAttribute("status", "Le menu à été bien modifier");
        return "redirect:/admin/produits";

    }
}
