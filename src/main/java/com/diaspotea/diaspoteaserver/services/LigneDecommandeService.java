package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.LigneDeCommande;
import com.diaspotea.diaspoteaserver.models.LigneDeCommandeProduit;
import com.diaspotea.diaspoteaserver.repository.LigneDeCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class LigneDecommandeService {
    private LigneDeCommandeRepository ligneDeCommandeRepository;
    @Autowired
    public LigneDecommandeService(LigneDeCommandeRepository ligneDeCommandeRepository){
        this.ligneDeCommandeRepository=ligneDeCommandeRepository;
    }

    public LigneDeCommande AjouterLigneDeCommande(LigneDeCommande ligneDeCommandeProduit) {
        return ligneDeCommandeRepository.save(ligneDeCommandeProduit);
    }

    public LigneDeCommande recupereLigneDeCommande(int id) {
        return ligneDeCommandeRepository.findById(id).orElse(null);
    }

    public LigneDeCommande modifierLigneDeCommande(LigneDeCommande ligneDeCommandeProduit) {
        return ligneDeCommandeRepository.save(ligneDeCommandeProduit);
    }

    public void deleteLigneDeCommande(int id) {
        ligneDeCommandeRepository.deleteById(id);
    }
}
