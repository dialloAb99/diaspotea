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
    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL)
    private List<LigneDeCommande> ligneDeCommandes;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Client client;
    private boolean etatPanier;

    public Panier() {
        ligneDeCommandes = new ArrayList<>();
        client = new Client();
    }

    public void ajouterLigneDeCommande(LigneDeCommande ligneDeCommande) {
        ligneDeCommandes.add(ligneDeCommande);

    }

    public boolean ligneDeCommandeProduitExiste(int produitId, int tailleID) {
        for (LigneDeCommande ligneDeCommande : ligneDeCommandes) {
            String className = ligneDeCommande.getClass().getSimpleName();
            if (className.equals("LigneDeCommandeProduit")) {
                Produit produit = ((LigneDeCommandeProduit) ligneDeCommande).getProduit();
                Taille taille = ((LigneDeCommandeProduit) ligneDeCommande).getTaille();
                if (produit.getId() == produitId && taille.getId() == tailleID) {
                    return true;
                }
            }

        }
        return false;
    }
    public boolean ligneDeCommandeMenuExiste(int menuId) {
        for (LigneDeCommande ligneDeCommande : ligneDeCommandes) {
            String className = ligneDeCommande.getClass().getSimpleName();
            if (className.equals("LigneDeCommandeMenu")) {
                Menu menu = ((LigneDeCommandeMenu) ligneDeCommande).getMenu();
               return menu.getId() == menuId;

            }
        }
        return false;
    }

    public LigneDeCommandeProduit recupererLigneDeCommandeProduit(int produitId, int tailleID) {
        for (LigneDeCommande ligneDeCommande : ligneDeCommandes) {
            String className = ligneDeCommande.getClass().getSimpleName();
            if (className.equals("LigneDeCommandeProduit")) {
                if (((LigneDeCommandeProduit) ligneDeCommande).getProduit().getId() == produitId && ((LigneDeCommandeProduit) ligneDeCommande).getTaille().getId() == tailleID) {
                    return (LigneDeCommandeProduit) ligneDeCommande;
                }
            }
        }
        return null;
    }

    public LigneDeCommandeMenu recupererLigneDeCommandeMenu(int menuId) {
        for (LigneDeCommande ligneDeCommande : ligneDeCommandes) {
            String className = ligneDeCommande.getClass().getSimpleName();
            if (className.equals("LigneDeCommandeMenu")) {
                Menu menu = ((LigneDeCommandeMenu) ligneDeCommande).getMenu();
                if (menu.getId() == menuId) {
                    return (LigneDeCommandeMenu) ligneDeCommande;
                }

            }
        }
        return null;
    }

    public void supprimerLigneDeCommande(LigneDeCommande ligneDeCommande) {
        ligneDeCommandes.remove(ligneDeCommande);
    }
}
