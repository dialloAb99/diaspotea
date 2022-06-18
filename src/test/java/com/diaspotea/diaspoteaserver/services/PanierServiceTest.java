package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Client;
import com.diaspotea.diaspoteaserver.models.LigneDeCommande;
import com.diaspotea.diaspoteaserver.models.Panier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

class PanierServiceTest {
    @Autowired
    private PanierService panierService;
    @Autowired
    private LigneDecommandeService ligneDecommandeService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Test
    @Transactional
    void AjouterPanier(){
        Panier panier=new Panier();
        LigneDeCommande ligneDeCommandeProduit =ligneDecommandeService.recupereLigneDeCommande(9);
        panier.ajouterLigneDeCommande(ligneDeCommandeProduit);
        Panier panierAjouter=panierService.ajouterPanier(panier);
        assertThat(panierAjouter).isEqualTo(panier);

    }
    @Test
    @Transactional
    void modifierPanier(){
        Panier panier=panierService.recuperePanier(1);
        LigneDeCommande ligneDeCommandeProduit =ligneDecommandeService.recupereLigneDeCommande(9);
        Client client=panier.getClient();
        panier.ajouterLigneDeCommande(ligneDeCommandeProduit);
        Panier panierModifier=panierService.modifierPanier(panier);
        assertThat(panierModifier).isEqualTo(panier);
    }
    @Test
    @Transactional
    void deletePanier(){
        panierService.deletePanier(1);
        Panier panier=panierService.recuperePanier(1);
        assertThat(panier).isNull();

    }

    @Test
    void panierEstActif() {
        Client client=utilisateurService.recuperUtilisateurParType(2, Client.class);
        boolean clientAunPanier=panierService.panierEstActif(client);
        assertThat(clientAunPanier).isTrue();
    }

    @Test
    void testPanierEstActif() {
        boolean clientAunPanier=panierService.panierEstActif(2);
        assertThat(clientAunPanier).isTrue();
    }
}