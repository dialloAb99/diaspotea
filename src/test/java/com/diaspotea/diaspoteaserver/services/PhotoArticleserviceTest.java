package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.PhotoArticle;
import com.diaspotea.diaspoteaserver.models.Produit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest

public class PhotoArticleserviceTest {
    @Autowired
    private PhotoArticleService photoArticleService;
    @Autowired
    private ProduitService produitService;
    @Test
    @Transactional
    void ajouterPhotoArticle(){
        PhotoArticle photoArticle= new PhotoArticle();
        Produit produit= produitService.recupererProduit(1);
        photoArticle.setProduit(produit);
        photoArticle.setId(1);
        PhotoArticle photoArticleAjouter=photoArticleService.ajouterPhotoArticle(photoArticle);
        assertThat(photoArticleAjouter).isEqualTo(photoArticle);
    }
    @Test
    @Transactional
    void modifierPhotoArticle(){
        PhotoArticle photoArticle=photoArticleService.recuperePhotoArticle(1);
        photoArticle.setLibele("photo thé noir");
        PhotoArticle photoArticleModifier=photoArticleService.modifierPhotoArticle(photoArticle);
        assertThat(photoArticleModifier).isEqualTo(photoArticle);
    }
    @Test
    @Transactional
    void deletePhotoArticle(){
        photoArticleService.deletePhotoArticle(1);
        PhotoArticle photoArticle=photoArticleService.recuperePhotoArticle(1);
        assertThat(photoArticle).isNull();

    }
}
