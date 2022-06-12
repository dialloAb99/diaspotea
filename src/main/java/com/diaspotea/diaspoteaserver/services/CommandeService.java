package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Commande;
import com.diaspotea.diaspoteaserver.repository.CommandeRepository;
import org.springframework.stereotype.Service;

@Service

public class CommandeService {
    private final CommandeRepository commandeRepository;
    public CommandeService(CommandeRepository commandeRepository){
        {this.commandeRepository=commandeRepository;}
    }

    public Commande ajouterCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public Commande recupereCommande(int id) {
        return commandeRepository.findById(id).orElse(null);
    }

    public Commande modifierCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    public void deleteCommande(int id) {
        commandeRepository.deleteById(id);
    }
}
