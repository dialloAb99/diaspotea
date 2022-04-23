package com.diaspotea.diaspoteaserver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "produit_tarif")
public class ProduitTarif {
    @EmbeddedId
    private  ProduitTarifID  produitTarifID;
    private Float tarif;
    @ManyToOne
    @MapsId("produitID")
    private Produit produit;
    @ManyToOne
    @MapsId("tailleID")
    private Taille taille;

    public ProduitTarif() {
        produitTarifID=new ProduitTarifID();
        tarif=0f;
        produit=new Produit();
        taille=new Taille();
    }

    public ProduitTarif(ProduitTarifID produitTarifID, Float tarif, Produit produit, Taille taille) {
        this.produitTarifID = produitTarifID;
        this.tarif = tarif;
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
        return produitTarifID.equals(that.produitTarifID) && Objects.equals(tarif, that.tarif) && produit.equals(that.produit) && taille.equals(that.taille);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produitTarifID, tarif, produit, taille);
    }
}
