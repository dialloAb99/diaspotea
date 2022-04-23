package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.Categorie;
import com.diaspotea.diaspoteaserver.models.Produit;
import com.diaspotea.diaspoteaserver.models.Type;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;




@SpringBootTest
public class TypeServiceTest {
    @Autowired
    private TypeService typeService;
    @Test
    @Transactional
    void ajouterType(){
        Produit produit=new Produit();
        produit.setId(6);
        Categorie categorie=new Categorie();
        categorie.setId(1);
        List<Produit>produits=new ArrayList<>();
        produits.add(produit);
        Type type=new Type();
        type.setNom("thé");
        Type typeCreer=typeService.ajouterUnType(type);
        assertThat(typeCreer.getNom()).isEqualTo("thé");

    }
    @Test
    @Transactional
    void modifierType(){
        Type typeRecupere=typeService.getType(1);
        typeRecupere.setNom("thé");
        Type modifiertype=typeService.modifierType(typeRecupere);
        assertThat(modifiertype.getNom()).isEqualTo("thé");

    }
    @Test
    @Transactional
    void supprimerUnType(){
        typeService.deleteType(1);
        Exception exception =assertThrows(JpaObjectRetrievalFailureException.class,()->{
            typeService.getType(1);
        });
        String expectedMessage="Unable to find com.diaspotea.diaspoteaserver.models.Type with id 1";
        String actualMessage=exception.getMessage();
       assertThat(actualMessage).contains(expectedMessage);
    }

}
