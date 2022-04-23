package com.diaspotea.diaspoteaserver.repository;

import com.diaspotea.diaspoteaserver.models.PhotoArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoArticleRepository extends JpaRepository<PhotoArticle, Integer> {
}