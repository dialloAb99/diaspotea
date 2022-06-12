package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Categorie;
import com.diaspotea.diaspoteaserver.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//pour dire à spring c'est un service
@Service
public class CategorieService {
    //c'est un attribut de la clase
    private final CategorieRepository categorieRepository ;
    @Autowired
    //un constructeur qui cree l'objet
    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

//c'est une methode
    public Categorie addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }
// methode pour ajouter une liste de categorie
    public ArrayList<Categorie> addCategories(ArrayList<Categorie> categories) {
        // qui retourne la liste des categorie save
        return (ArrayList<Categorie>) categorieRepository.saveAll(categories);
    }
// retourne une categorie                   //retour un categorie à l'id ou une categorie vide
    public Categorie getCategorie(int id) {
        return categorieRepository.findById(id).orElse(new Categorie());
    }
// sa retourne la categorie modifier et save
    public Categorie modifierUneCategorie(Categorie categorie) {
       return categorieRepository.save(categorie);
    }
//declaration d'une methode
    public void deleteCategorie(int id) {
        //methode pour supprimer une categorie par son identifiant
        categorieRepository.deleteById(id);
    }
}
