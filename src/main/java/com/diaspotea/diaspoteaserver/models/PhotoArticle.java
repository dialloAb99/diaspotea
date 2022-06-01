package com.diaspotea.diaspoteaserver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoArticle {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    private String libele;
    private String url;
    @ManyToOne
    @JoinColumn(name="produit_id")
    private Produit produit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoArticle that = (PhotoArticle) o;
        if(produit==null) return false;
        return   id == that.id && Objects.equals(libele, that.libele) && Objects.equals(url, that.url) && produit.equals(that.produit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libele, url, produit);
    }
}
