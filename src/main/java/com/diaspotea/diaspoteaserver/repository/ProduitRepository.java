package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {

   List<Produit> findByCategories_NomContaining(String nom);

}