package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Commande;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class CommandeServiceTest {
    @Autowired
    private CommandeService commandeService;
    @Test
    @Transactional
    void ajouterCommande(){
        Commande commande=new Commande();
        Commande commandeAjouter=commandeService.ajouterCommande(commande);
        assertThat(commandeAjouter).isEqualTo(commande);
    }
    @Test
    @Transactional
    void modifierCommande(){
        Commande commande=commandeService.recupereCommande(4);
        commande.setAdresseLivraison("143 rue du tonton pat le prof 94300 bonneuil");
        Commande commandeModifier=commandeService.modifierCommande(commande);
        assertThat(commandeModifier).isEqualTo(commande);
    }
    @Test
    @Transactional
    void supprimerCommande(){
        commandeService.deleteCommande(4);
        Commande commande=commandeService.recupereCommande(4);
        assertThat(commande).isNull();
    }
}
