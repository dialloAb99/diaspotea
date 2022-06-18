package com.diaspotea.diaspoteaserver.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class CreerProduitDto implements Serializable {
    private final String nom;
    private final String description;
    private final Float prix;
    private final Integer tailleId;
    private final Integer typeId;
    private  final MultipartFile photo;
}
