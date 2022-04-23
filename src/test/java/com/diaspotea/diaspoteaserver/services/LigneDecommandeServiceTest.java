package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.LigneDeCommande;
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
        LigneDeCommande ligneDeCommande=new LigneDeCommande();
        LigneDeCommande ligneDeCommandeAjouter=ligneDecommandeService.AjouterLigneDeCommande(ligneDeCommande);
        assertThat(ligneDeCommandeAjouter).isEqualTo(ligneDeCommande);
    }
    @Test
    @Transactional
    void modifierLigneDeCommande(){
        LigneDeCommande ligneDeCommande=ligneDecommandeService.recupereLigneDeCommande(9);
        ligneDeCommande.setQuantiter(3);
        LigneDeCommande ligneDeCommandeModifier=ligneDecommandeService.modifierLigneDeCommande(ligneDeCommande);
        assertThat(ligneDeCommandeModifier).isEqualTo(ligneDeCommande);
    }
    @Test
    @Transactional
    void supprimerLigneDeCommande(){
        ligneDecommandeService.deleteLigneDeCommande(9);
        LigneDeCommande ligneDeCommande=ligneDecommandeService.recupereLigneDeCommande(9);
        assertThat(ligneDeCommande).isNull();
    }
}
