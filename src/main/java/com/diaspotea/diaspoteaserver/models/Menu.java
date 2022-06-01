package com.diaspotea.diaspoteaserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
