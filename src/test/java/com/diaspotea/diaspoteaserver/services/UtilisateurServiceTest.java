package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Livreur;
import com.diaspotea.diaspoteaserver.models.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class UtilisateurServiceTest {
    @Autowired
    private UtilisateurService utilisateurService;

    @Test
    @Transactional
    void ajouterUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        Utilisateur utilisateurAjouter = utilisateurService.ajouterUtilisateur(utilisateur);
        assertThat(utilisateurAjouter).isEqualTo(utilisateur);
    }

    @Test
    @Transactional
    void modifierUtilisateur() {
        Utilisateur utilisateur = utilisateurService.recupereUtilisateur(1);
        utilisateur.setEmail("Patrick");
        Utilisateur utilisateurModifier = utilisateurService.modifierUtilisateur(utilisateur);
        assertThat(utilisateurModifier).isEqualTo(utilisateur);
    }
    @Test
    @Transactional
    void supprimerUtilisateur(){
        utilisateurService.deleteUtilisateur(1);
        Utilisateur utilisateur=utilisateurService.recupereUtilisateur(1);
        assertThat(utilisateur).isNull();
    }
    @Test
    void recupererUtilisateurLivreur(){
     Livreur utilisateur= (Livreur) utilisateurService.recupereUtilisateur(3);
     assertThat(utilisateur).isNotNull();
        assertThat(utilisateur.getId()).isEqualTo(3);


    }
}
