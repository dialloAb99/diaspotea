package com.diaspotea.diaspoteaserver.dto;

import com.diaspotea.diaspoteaserver.models.Produit;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MenuDto implements Serializable {
    private  final Integer id;
    private final String nom;
    private final String description;
    private final float prix;
}
