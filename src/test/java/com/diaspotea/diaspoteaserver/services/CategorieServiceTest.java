package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Categorie;
import com.diaspotea.diaspoteaserver.models.Produit;
import com.diaspotea.diaspoteaserver.models.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//pour dire d'utiliser sprinboot lors du test
@SpringBootTest
class CategorieServiceTest {
    //dire a springboot d'instancier l'objet(creation d'un bean)
    @Autowired
    private CategorieService categorieService;

    //cette methode dois s'excuter avant chaque test
    @BeforeEach
    void setUp() {
    }
    // c'est la classe de test
    @Test
    //le test n'a aucune repercution sur la base de donnée
    @Transactional
    //declaration d'une méthode
    void ajouterUneCategorie(){
        //declaration d'une variable
        List<Produit> produits;
        //création ou affectaion d'une liste
        produits=new ArrayList<>();
        //creation d'une categorie
        Categorie categorie=new Categorie(1, "boisson chaude",produits);
        //affectation d'une valeur retourner par la methode addCategorie
        Categorie categorieAjouter= categorieService.addCategorie(categorie);
        //test que la methode ajouter n'es pas null
        assertThat(categorieAjouter).isNotNull();

    }
    @Test
    @Transactional
    void ajouterDesCategories(){
        List<Produit> produits;
        produits=new ArrayList<>();
        Categorie categorie=new Categorie(1, "boisson chaude",produits);
        Categorie categorie2=new Categorie(2, "boisson froide", produits);
        ArrayList<Categorie> categories=new ArrayList<>();
        //ajout categorie aà la liste
        categories.add(categorie);
        categories.add(categorie2);
        //ajouter et recuperation de la liste créer des categories
        ArrayList<Categorie> categoriesAjouters= categorieService.addCategories(categories);
        assertThat(categoriesAjouters).isNotNull();

    }
    @Test
    @Transactional
    void modifierUneCategorie(){
        // recuperation de la categorie qui porte l'identifiant 1
        Categorie categorieRecuperer=categorieService.getCategorie(1);
        //modification de son nom
        categorieRecuperer.setNom("boisson froide");
        //j'ai sauvegarder la modification de la categorie dans la base de données
        Categorie categorieModifier=categorieService.modifierUneCategorie(categorieRecuperer);
        //je test le nom de la categorie modifier egale à boisson froide
        assertThat(categorieModifier.getNom()).isEqualTo("boisson froide");
    }
    @Test
    @Transactional
    void supprimerUneCategorie(){
        //j'ai fais appel à une methode pour supprimer la categorie qui porte id 1
        categorieService.deleteCategorie(1);
        //j'ai recuperer la categorie qui porte id 1
        Categorie categorie=categorieService.getCategorie(1);

        assertThat(categorie.getNom()).isNull();

    }

}