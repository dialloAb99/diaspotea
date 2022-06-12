package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.models.Utilisateur;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {
    boolean existsByEmail(String email);
    Utilisateur findByEmail(String username);
}