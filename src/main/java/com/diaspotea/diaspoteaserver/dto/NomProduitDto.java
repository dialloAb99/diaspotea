package com.diaspotea.diaspoteaserver.dto;

import com.diaspotea.diaspoteaserver.models.ProduitTarifID;
import lombok.Data;

import java.io.Serializable;

@Data
public class NomProduitDto implements Serializable {
    private final ProduitTarifID produitTarifID;
    private final String nom;
    private  final TailleDto taille;
}
