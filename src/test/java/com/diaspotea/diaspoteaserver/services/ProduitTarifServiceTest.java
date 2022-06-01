package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Produit;
import com.diaspotea.diaspoteaserver.models.ProduitTarif;
import com.diaspotea.diaspoteaserver.models.ProduitTarifID;
import com.diaspotea.diaspoteaserver.models.Taille;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class ProduitTarifServiceTest {
    @Autowired
    private ProduitTarifService produitTarifService;
    @Autowired
    private ProduitService produitService;
    @Autowired
    private TailleService tailleService;
    @Test
    @Transactional
    void ajouterProduitTarif(){
        ProduitTarif produitTarif= new ProduitTarif();
        produitTarif.setProduitTarifId(1, 10);
        Produit produit=produitService.recupererProduit(1);
        Taille taille=tailleService.recupererTaille(10);
        produitTarif.setProduit(produit);
        produitTarif.setTaille(taille);
        ProduitTarif produitTarifAjouter=produitTarifService.ajouterProduitTarif(produitTarif);
        assertThat(produitTarifAjouter).isEqualTo(produitTarif);
    }
    @Test
    @Transactional
    void modifierProduitTarif(){
        ProduitTarifID produitTarifId= new ProduitTarifID(1, 10);
        ProduitTarif produitTarif=produitTarifService.recupereProduitTarif(produitTarifId);
        produitTarif.setPrix(3.5f);
        ProduitTarif produitTarifModifier=produitTarifService.modifierProduitTarif(produitTarif);
        assertThat(produitTarifModifier).isEqualTo(produitTarif);
    }
    @Test
    @Transactional
    void supprimerProduitTarif(){
        ProduitTarifID produitTarifID=new ProduitTarifID(1,10);
        produitTarifService.deleteProduitTarif(produitTarifID);
        ProduitTarif produitTarif=produitTarifService.recupereProduitTarif(produitTarifID);
        assertThat(produitTarif).isNull();
    }
    @Test
    void recupererProduitTarif(){
     ProduitTarif  produitTarifRecuperer=produitTarifService.recupereProduitTarif(new ProduitTarifID(1,10));
     assertThat(produitTarifRecuperer.getProduitTarifID()).isEqualTo(new ProduitTarifID(1,10));

    }
}
