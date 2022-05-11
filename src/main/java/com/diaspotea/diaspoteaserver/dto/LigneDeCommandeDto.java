package com.diaspotea.diaspoteaserver.dto;

import com.diaspotea.diaspoteaserver.models.LigneDeCommande;
import com.diaspotea.diaspoteaserver.models.Produit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class LigneDeCommandeDto extends LigneDeCommande implements Serializable  {
    private final int id;
    private final Produit produit;
    private final TailleDto taille;


}
