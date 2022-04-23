package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Produit;
import com.diaspotea.diaspoteaserver.models.Taille;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

@SpringBootTest

public class TailleServiceTest {
    @Autowired
    private TailleService tailleService;

    @Test
    @Transactional
    void ajouterTaille() {
        Taille taille = new Taille();
        taille.setId(10);
        Taille tailleAjouter = tailleService.ajouterUneTaille(taille);
        assertThat(tailleAjouter.getId()).isEqualTo(taille.getId());
    }

    @Test
    @Transactional
    void modofierTaille() {
        Taille taille = tailleService.recupererTaille(10);
        Taille tailleModifier = tailleService.modifierUneTaille(taille);
        assertThat(tailleModifier.getId()).isEqualTo(taille.getId());
    }
    @Test
    @Transactional
    void deleteTaille(){
        tailleService.deleteTaille(10);
        Taille taille=tailleService.recupererTaille(10);
        assertThat(taille).isNull();
    }
}
