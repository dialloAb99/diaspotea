package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Client;
import com.diaspotea.diaspoteaserver.models.Panier;
import com.diaspotea.diaspoteaserver.repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PanierService {
    private final PanierRepository panierRepository;
    @Autowired
    public PanierService(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
    }

    public Panier ajouterPanier(Panier panier) {
        return panierRepository.save(panier);
    }

    public Panier recuperePanier(int id) {
        return panierRepository.findById(id).orElse(null);
    }

    public Panier modifierPanier(Panier panier) {
        return panierRepository.save(panier);

    }

    public void deletePanier(int id) {
        panierRepository.deleteById(id);
    }
    public boolean panierEstActif(Client client){
        return panierRepository.existsPanierByEtatPanierIsTrueAndClient(client);
    }
    public boolean panierEstActif(int idUtilisateur){
        return panierRepository.existsPanierByEtatPanierIsTrueAndClient_Id(idUtilisateur);
    }
    public Panier recuperePanierActif(Client client) {
        return panierRepository.findPanierByClientAndEtatPanierIsTrue(client);
    }

    public Panier recuperePanierActif(int idUtilisateur) {
        return panierRepository.findPanierByClient_IdAndAndEtatPanierIsTrue(idUtilisateur);
    }
}

