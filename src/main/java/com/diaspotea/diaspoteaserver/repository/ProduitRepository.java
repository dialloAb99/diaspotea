package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {

   List<Produit> findByCategorie_NomContaining(String nom);

}