package com.diaspotea.diaspoteaserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class LigneDeCommande {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    private int quantiter;
    private float prix;
    @ManyToOne
    @JoinColumn(name="commande_id")
    private Commande commande;
    @ManyToOne
    @JoinColumn(name="produit_id")
    private Produit produit;
    @ManyToOne
    @JoinColumn(name="taille_id")
    private Taille taille;
    @ManyToOne
    private Panier panier;


}
