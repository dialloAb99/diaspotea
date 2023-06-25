package com.diaspotea.diaspoteaserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import java.util.List;

@Entity
@Getter
@Setter

public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String description;
    private  float prix;
    @Column(name = "image_url")
    private String imageUrl;
    @ManyToMany(mappedBy = "menus")
    private List<ProduitTarif> produits;
    @OneToMany(mappedBy = "menu")
    private List<LigneDeCommandeMenu> ligneDeCommandeMenus;
    @PreRemove
    private  void  beforeRemove(){
        removeLigneDeCommande();
        removeProduits();
    }
    private   void removeLigneDeCommande(){
        this.ligneDeCommandeMenus.forEach(ligneDeCommandeMenu -> ligneDeCommandeMenu.setMenu(null));
        this.ligneDeCommandeMenus.clear();
    }
    private   void removeProduits() {
        this.produits.forEach(produit -> produit.removeMenu(this));
        this.produits.clear();
    }
}
