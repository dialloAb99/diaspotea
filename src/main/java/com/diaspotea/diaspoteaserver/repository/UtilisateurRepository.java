package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.models.Utilisateur;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository extends CrudRepository<Utilisateur, Integer> {


    boolean existsByEmail(String email);
    Utilisateur findByEmail(String username);
    //c'est une methode generique qui permet de recuperer un utilisateur du type specifier dans les parametre
    <T extends Utilisateur> T findById(Integer id,Class<T> type);

}