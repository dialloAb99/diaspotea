package com.diaspotea.diaspoteaserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter

public class Produit {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    private String nom;
    private String description;
    @OneToMany(mappedBy = "produit",cascade = CascadeType.ALL)
    private List<PhotoArticle>photoArticles;
    @ManyToOne
    @JoinColumn(name="type_id")
    private Type typeProduit;
    @OneToMany(mappedBy = "produit")
    private List<LigneDeCommandeProduit>ligneDeCommandes;
    @OneToMany(mappedBy = "produit",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProduitTarif>produitTarifs;

    public Produit() {
        produitTarifs=new ArrayList<>();
        ligneDeCommandes=new ArrayList<>();
        photoArticles=new ArrayList<>();


    }

    @ManyToOne
    private Categorie categorie;




    public void removeProduitTarif(int produitId, int tailleId) {
        produitTarifs.removeIf(produitTarif -> produitTarif.getProduit().getId() == produitId && produitTarif.getTaille().getId() == tailleId);
    }

    public void ajouterProduitTarif(ProduitTarif produitTarif) {
        produitTarifs.add(produitTarif);
    }
}
