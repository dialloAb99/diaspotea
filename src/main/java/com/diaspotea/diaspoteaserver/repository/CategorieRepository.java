package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.models.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    Categorie findByNom(String Categorie);
    Categorie findByNomContaining(String Categorie);
}