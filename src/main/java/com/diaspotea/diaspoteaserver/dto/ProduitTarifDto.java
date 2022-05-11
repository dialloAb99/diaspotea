package com.diaspotea.diaspoteaserver.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProduitTarifDto implements Serializable {
    private final int produitTarifIDProduitID;
    private final int produitTarifIDTailleID;
    private final Float tarif;
    private final TailleDto taille;
}
