package com.diaspotea.diaspoteaserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "produit_tarif")
public class ProduitTarif {
    @EmbeddedId
    private  ProduitTarifID  produitTarifID;
    private Float prix;
    @ManyToMany
    @JoinTable(name="produit_menu",
            joinColumns={@JoinColumn(name="produit_id"),@JoinColumn(name = "taille_id")},
            inverseJoinColumns={@JoinColumn(name="menu_id")})
    private List<Menu> menus;
    @ManyToOne
    @MapsId("produitID")
    private Produit produit;
    @ManyToOne
    @MapsId("tailleID")
    private Taille taille;

    public ProduitTarif() {
        produitTarifID=new ProduitTarifID();
        prix =0f;
        produit=new Produit();
        taille=new Taille();
    }

    public ProduitTarif(ProduitTarifID produitTarifID, Float prix, Produit produit, Taille taille) {
        this.produitTarifID = produitTarifID;
        this.prix = prix;
        this.produit = produit;
        this.taille = taille;
    }

    public void setProduitTarifId(int produitId, int tailleId) {
        produitTarifID.setProduitID(produitId);
        produitTarifID.setTailleID(tailleId);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduitTarif that = (ProduitTarif) o;
        return produitTarifID.equals(that.produitTarifID) && Objects.equals(prix, that.prix) && produit.equals(that.produit) && taille.equals(that.taille);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produitTarifID, prix, produit, taille);
    }

    public void removeMenu(Menu menu) {
        menus.remove(menu);
    }
}
