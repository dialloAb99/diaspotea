package com.diaspotea.diaspoteaserver.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProduitDto implements Serializable {
    private  final Integer id;
    private final String nom;
    private final String description;
    private final List<PhotoArticleDto> photoArticles;
    private final TypeProduitDto typeProduit;
    private final List<TypeProduitDto> typeProduitDtos;
   private  final  ProduitTarifDto produitTarifDto;


}
