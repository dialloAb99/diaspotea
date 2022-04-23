package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Commune;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class CommuneServiceTest {
    @Autowired
    private CommuneService communeService;
    @Test
    @Transactional
    void ajouterCommune(){
        Commune commune=new Commune();
        Commune communeAjouter=communeService.ajouterCommune(commune);
        assertThat(communeAjouter).isEqualTo(commune);
    }
    @Test
    @Transactional
    void modifierCommune(){
        Commune commune=communeService.recupereCommune(1);
        commune.setNom("boissy");
        Commune communeModifier=communeService.modifierCommune(commune);
        assertThat(communeModifier).isEqualTo(commune);
    }
    @Test
    @Transactional
    void supprimerCommune(){
        communeService.deleteCommune(1);
        Commune commune=communeService.recupereCommune(1);
        assertThat(commune);
    }
}
