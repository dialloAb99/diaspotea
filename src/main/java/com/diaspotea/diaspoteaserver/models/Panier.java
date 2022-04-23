package com.diaspotea.diaspoteaserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Panier() {
        ligneDeCommandes=new ArrayList<>();
        client=new Client();
    }

    @OneToMany(mappedBy = "panier")
    private List<LigneDeCommande> ligneDeCommandes;
    @ManyToOne
    private Client client;

    public void ajouterLigneDeCommande(LigneDeCommande ligneDeCommande) {
        ligneDeCommandes.add(ligneDeCommande);

    }
    private boolean etatPanier;

    public boolean ligneDeCommandeExiste(int produitId, int tailleID) {
        for (LigneDeCommande ligneDeCommande:ligneDeCommandes) {
            Produit produit=ligneDeCommande.getProduit();
            Taille taille=ligneDeCommande.getTaille();
            if(produit.getId()==produitId && taille.getId()==tailleID){
                return  true;
            }
        }
        return  false;
    }

    public LigneDeCommande recupererLigneDeCommande(int produitId, int tailleID){
        for (LigneDeCommande ligneDeCommande:ligneDeCommandes) {
            if (ligneDeCommande.getProduit().getId() == produitId && ligneDeCommande.getTaille().getId() == tailleID){
                return ligneDeCommande;
            }
        }
        return null;
    }
}
