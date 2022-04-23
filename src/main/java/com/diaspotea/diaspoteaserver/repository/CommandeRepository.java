package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {
}