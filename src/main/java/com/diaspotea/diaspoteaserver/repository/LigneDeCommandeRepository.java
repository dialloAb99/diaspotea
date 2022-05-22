package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.models.LigneDeCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneDeCommandeRepository extends JpaRepository<LigneDeCommande, Integer> {
}