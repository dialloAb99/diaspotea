package com.diaspotea.diaspoteaserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    @OneToMany(mappedBy = "produit")
    private List<PhotoArticle>photoArticles;
    @ManyToOne
    @JoinColumn(name="type_id")
    private Type typeProduit;
    @OneToMany(mappedBy = "produit")
    private List<LigneDeCommandeProduit>ligneDeCommandes;
    @OneToMany(mappedBy = "produit")
    private List<ProduitTarif>produitTarifs;
    @ManyToMany
    @JoinTable(name="produit_menu",
            joinColumns={@JoinColumn(name="produit_id")},
            inverseJoinColumns={@JoinColumn(name="menu_id")})
    private List<Menu>menus;
    @ManyToMany
    @JoinTable(name="produit_categorie",
            joinColumns={@JoinColumn(name="produit_id")},
            inverseJoinColumns={@JoinColumn(name="categorie_id")})
    private List<Categorie>categories;

    public void addCategorie(Categorie categorie) {
        categories.add(categorie);
    }
}
