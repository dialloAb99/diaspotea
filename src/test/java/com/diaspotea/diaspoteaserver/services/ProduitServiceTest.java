package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Categorie;
import com.diaspotea.diaspoteaserver.models.Produit;
import com.diaspotea.diaspoteaserver.models.ProduitTarif;
import com.diaspotea.diaspoteaserver.models.ProduitTarifID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest

public class ProduitServiceTest {
    @Autowired
    private ProduitService produitService;
    @Autowired
    private CategorieService categorieService;

    @Autowired
    private ProduitTarifService produitTarifService;

    @Test
    @Transactional

    void ajouterProduit(){
        Produit produit=new Produit();
        produit.setId(1);
        Produit produitAjouter=produitService.ajouterProduit(produit);
        assertThat(produitAjouter.getId()).isEqualTo(produit.getId());

    }
    @Test
    @Transactional
    void modifierProduit(){
        Produit produit=produitService.recupererProduit(1);
        produit.setDescription("Tysane au Yata");
        Produit produitSauvegarder=produitService.modifierProduit(produit);
        assertThat(produitSauvegarder.getDescription()).isEqualTo(produit.getDescription());
    }
    @Test
    @Transactional
    void supprimerProduit(){
        produitService.deleteProduit(1);
        Produit produit=produitService.recupererProduit(1);
        assertThat(produit).isNull();
    }
    @Test
    @Transactional
    void ajouterCategorieAuProduit(){
        Categorie categorie=categorieService.getCategorie(1);
        Produit produit=produitService.recupererProduit(1);
        produit.addCategorie(categorie);
        Produit ajouterCategorieAuProduit=produitService.modifierProduit(produit);
        assertThat(ajouterCategorieAuProduit.getCategories())
                .asList()
                .contains(categorie);
    }
    @Test
    @Transactional
    void ProduitAunProduitTarif(){
        ProduitTarif produitTarifRecuperer=produitTarifService.recupereProduitTarif(new ProduitTarifID(1,10));
        Produit produit=produitService.recupererProduit(1);
        ProduitTarif produitTarif=produit.getProduitTarifs().get(0);
        assertThat(produitTarifRecuperer).isEqualTo(produitTarif);
    }
}
