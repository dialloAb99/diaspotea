package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.models.LigneDeCommande;
import com.diaspotea.diaspoteaserver.models.LigneDeCommandeProduit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneDeCommandeRepository extends JpaRepository<LigneDeCommande, Integer> {
}