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
    private List<Produit> produits;
    @OneToMany(mappedBy = "menu")
    private List<LigneDeCommandeMenu> ligneDeCommandeMenus;
}
