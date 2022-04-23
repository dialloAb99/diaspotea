package com.diaspotea.diaspoteaserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class Taille {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "taille")
    private List<LigneDeCommande>ligneDeCommandes;
    @OneToMany(mappedBy = "produit")
    private List<ProduitTarif>produits;


    }
