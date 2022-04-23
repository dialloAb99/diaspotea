package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Livreur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class LivreurServiceTest {
    @Autowired
    private LivreurService livreurService;
    @Test
    @Transactional
    void ajouterLivreur(){
        Livreur livreur=new Livreur();
        Livreur livreurAjouter=livreurService.ajouterLivreur(livreur);
        assertThat(livreurAjouter).isEqualTo(livreur);
    }
    @Test
    @Transactional
    void modifierLivreur(){
        Livreur livreur=livreurService.recupereLivreur(2);
        livreur.setName("grand professeur");
        Livreur livreurModifier=livreurService.modifierLivreur(livreur);
        assertThat(livreurModifier).isEqualTo(livreur);
    }
    @Test
    @Transactional
    void supprimerLivreur(){
        livreurService.deleteLivreur(2);
        Livreur livreur=livreurService.recupereLivreur(2);
        assertThat(livreur).isNull();
    }
}
