package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Client;
import com.diaspotea.diaspoteaserver.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class ClientServiceTest {
    @Autowired
    private UtilisateurService utilisateurService;
    private ClientRepository clientRepository;
    @Test
    @Transactional
    void ajouterClient(){
        Client client=new Client();
        Client clientAjouter=(Client) utilisateurService.ajouterUtilisateur(client);
        assertThat(clientAjouter).isEqualTo(client);
    }
    @Test
    @Transactional
    void modifierClient(){
        Client client=utilisateurService.recuperUtilisateurParType(2, Client.class);;
        client.setNom("ronaldo");
        Client clientModifier=(Client) utilisateurService.modifierUtilisateur(client);
        assertThat(clientModifier).isEqualTo(client);
    }
    @Test
    @Transactional
    void supprimerClient(){
        utilisateurService.deleteUtilisateur(2);
        Client client=utilisateurService.recuperUtilisateurParType(2, Client.class);
        assertThat(client).isNull();
    }
}
