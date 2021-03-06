package com.diaspotea.diaspoteaserver.dto;

import com.diaspotea.diaspoteaserver.models.ProduitTarifID;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProduitTarifDto implements Serializable {
    private final ProduitTarifID produitTarifID;
    private final Float prix;
    private final TailleDto taille;
}
