package com.diaspotea.diaspoteaserver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
//table d'une base donnée
@Entity
//genere le constructeur avec tous les parametre de sa classe
@AllArgsConstructor
// genere le constructeur sans parametre
@NoArgsConstructor
//genre les mutateurs(modifier les attribut de la classe) des parametre
@Setter
//genre les accesseur(methode pour recuperer les atttribiuts) des attributs
@Getter
public class Categorie {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    private  String nom;
    @OneToMany(mappedBy = "categorie")
    private List<Produit>produits;
}
