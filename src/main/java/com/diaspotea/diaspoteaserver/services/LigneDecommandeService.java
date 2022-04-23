package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.LigneDeCommande;
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

    public LigneDeCommande AjouterLigneDeCommande(LigneDeCommande ligneDeCommande) {
        return ligneDeCommandeRepository.save(ligneDeCommande);
    }

    public LigneDeCommande recupereLigneDeCommande(int id) {
        return ligneDeCommandeRepository.findById(id).orElse(null);
    }

    public LigneDeCommande modifierLigneDeCommande(LigneDeCommande ligneDeCommande) {
        return ligneDeCommandeRepository.save(ligneDeCommande);
    }

    public void deleteLigneDeCommande(int id) {
        ligneDeCommandeRepository.deleteById(id);
    }
}
