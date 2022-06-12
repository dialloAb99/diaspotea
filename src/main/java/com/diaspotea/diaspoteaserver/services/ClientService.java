package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Client;
import com.diaspotea.diaspoteaserver.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service

public class ClientService {
    private final ClientRepository clientRepository;
    public ClientService (ClientRepository clientRepository){
        {this.clientRepository=clientRepository;}
    }

    public Client ajouterClient(Client client) {
        return clientRepository.save(client);
    }

    public Client recupereClient(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client modifierClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }
}
