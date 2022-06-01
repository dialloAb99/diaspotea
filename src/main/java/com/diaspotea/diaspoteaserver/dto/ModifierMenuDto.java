package com.diaspotea.diaspoteaserver.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ModifierMenuDto implements Serializable {
    private final int id;
    private final String nom;
    private final String description;
    private final float prix;
    private final List<NomProduitDto> produits;

}
