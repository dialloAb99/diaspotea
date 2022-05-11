package com.diaspotea.diaspoteaserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Getter
@Setter
@Entity
@Inheritance(strategy=SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE", discriminatorType=STRING)
public abstract   class LigneDeCommande {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="commande_id")
    private Commande commande;
    @ManyToOne
    private Panier panier;
    private int quantiter;
    private float prix;
    public void augmenteQuantiter(){
        quantiter++;
    }
    public void diminueQuantiter(){
        if (quantiter>0){
            quantiter--;
        }
    }
}
