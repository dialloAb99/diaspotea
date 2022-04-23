package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class ClientServiceTest {
    @Autowired
    private ClientService clientService;
    @Test
    @Transactional
    void ajouterClient(){
        Client client=new Client();
        Client clientAjouter=clientService.ajouterClient(client);
        assertThat(clientAjouter).isEqualTo(client);
    }
    @Test
    @Transactional
    void modifierClient(){
        Client client=clientService.recupereClient(1);
        client.setNom("ronaldo");
        Client clientModifier=clientService.modifierClient(client);
        assertThat(clientModifier).isEqualTo(client);
    }
    @Test
    @Transactional
    void supprimerClient(){
        clientService.deleteClient(1);
        Client client=clientService.recupereClient(1);
        assertThat(client).isNull();
    }
}
