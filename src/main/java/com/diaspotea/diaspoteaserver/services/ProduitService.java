package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Categorie;
import com.diaspotea.diaspoteaserver.models.Produit;
import com.diaspotea.diaspoteaserver.repository.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProduitService {
    private final ProduitRepository produitRepository;
    public ProduitService(ProduitRepository produitRepository){
        {this.produitRepository=produitRepository;}

    }

    public Produit ajouterProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit recupererProduit(int id) {
        return produitRepository.findById(id).orElse(null);
    }

    public Produit modifierProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public void deleteProduit(int id) {
        produitRepository.deleteById(id);
    }
    public void deleteProduit(Produit produit) {
        produitRepository.delete(produit);
    }

    public void deleteProduitTarif(int i, int i1) {
    }

    public List<Produit> recupereToutProduits() {
        return produitRepository.findAll();
    }

    public List<Produit> recupererProduitParCategorie(Categorie categorie) {
        return produitRepository.findByCategoriesLike(categorie);

    }
}
