package com.diaspotea.diaspoteaserver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value="ligneDeCommandeProduit")
public class LigneDeCommandeProduit extends LigneDeCommande {
    @ManyToOne
    @JoinColumn(name="produit_id")
    private Produit produit;
    @ManyToOne
    @JoinColumn(name="taille_id")
    private Taille taille;




}
