package com.diaspotea.diaspoteaserver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Type{
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    private String nom;
    @OneToMany(mappedBy = "typeProduit")
    private List<Produit>produits;

    public void setNom(String nouveauNom) {
        nom=nouveauNom;
    }
}
