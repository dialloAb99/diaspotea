package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.LigneDeCommande;
import com.diaspotea.diaspoteaserver.models.LigneDeCommandeProduit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class LigneDecommandeServiceTest {
    @Autowired
    private LigneDecommandeService ligneDecommandeService;
    @Test
    @Transactional
    void ajouterLigneDeCommande(){
        LigneDeCommandeProduit ligneDeCommandeProduit =new LigneDeCommandeProduit();
        LigneDeCommande ligneDeCommandeAjouter=ligneDecommandeService.AjouterLigneDeCommande(ligneDeCommandeProduit);
        assertThat(ligneDeCommandeAjouter).isEqualTo(ligneDeCommandeProduit);
    }
    @Test
    @Transactional
    void modifierLigneDeCommande(){
        LigneDeCommande ligneDeCommandeProduit =ligneDecommandeService.recupereLigneDeCommande(9);
        ligneDeCommandeProduit.setQuantiter(3);
        LigneDeCommande ligneDeCommandeModifier=ligneDecommandeService.modifierLigneDeCommande(ligneDeCommandeProduit);
        assertThat(ligneDeCommandeModifier).isEqualTo(ligneDeCommandeProduit);
    }
    @Test
    @Transactional
    void supprimerLigneDeCommande(){
        ligneDecommandeService.deleteLigneDeCommande(9);
        LigneDeCommande ligneDeCommandeProduit =ligneDecommandeService.recupereLigneDeCommande(9);
        assertThat(ligneDeCommandeProduit).isNull();
    }
}
