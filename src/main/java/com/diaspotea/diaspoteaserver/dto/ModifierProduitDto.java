package com.diaspotea.diaspoteaserver.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ModifierProduitDto implements Serializable {
    private final int id;
    private final String nom;
    private final String description;
    private final List<PhotoArticleDto> photoArticles;
    private final TypeProduitDto typeProduit;
    private final List<TypeProduitDto> typeProduitDtos;
    private final  float prix;
    private  final int tailleId;


}
