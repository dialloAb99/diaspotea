package com.diaspotea.diaspoteaserver.services;

import com.diaspotea.diaspoteaserver.models.PhotoArticle;
import com.diaspotea.diaspoteaserver.repository.PhotoArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PhotoArticleService {
    private final PhotoArticleRepository photoArticleRepository;
    @Autowired
    public PhotoArticleService(PhotoArticleRepository photoArticleRepository){
        {this.photoArticleRepository=photoArticleRepository;}

    }

    public PhotoArticle ajouterPhotoArticle(PhotoArticle photoArticle) {
        return photoArticleRepository.save(photoArticle);
    }

    public PhotoArticle recuperePhotoArticle(int id) {
        return photoArticleRepository.findById(id).orElse(null);
    }

    public PhotoArticle modifierPhotoArticle(PhotoArticle photoArticle) {
        return  photoArticleRepository.save(photoArticle);
    }

    public void deletePhotoArticle(int id) {
        photoArticleRepository.deleteById(id);
    }
}
