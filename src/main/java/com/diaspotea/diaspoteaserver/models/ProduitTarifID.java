package com.diaspotea.diaspoteaserver.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProduitTarifID implements Serializable {
    @Column(name = "produit_id")
    private  int produitID;
    @Column(name = "taille_id")
    private  int tailleID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProduitTarifID)) return false;
        ProduitTarifID that = (ProduitTarifID) o;
        return produitID == that.produitID && tailleID == that.tailleID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(produitID, tailleID);
    }
}
