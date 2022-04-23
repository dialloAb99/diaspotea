package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.models.Commande;
import com.diaspotea.diaspoteaserver.models.Commune;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface CommuneRepository extends JpaRepository<Commune, Integer> {
    }

