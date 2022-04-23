package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.models.Client;
import com.diaspotea.diaspoteaserver.models.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier, Integer> {
    boolean existsPanierByEtatPanierIsTrueAndClient(Client client);
    boolean existsPanierByEtatPanierIsTrueAndClient_Id(int idUtilisateur);
    //j'ai ajouter une signature à l'interface permettant de recupere le panier actif d'un client
    Panier findPanierByClientAndEtatPanierIsTrue(Client client);

    Panier findPanierByClient_IdAndAndEtatPanierIsTrue(int idUtilisateur);
}
