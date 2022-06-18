package com.diaspotea.diaspoteaserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Objects;
import java.util.Set;

@Entity
@Setter
@Getter
@DiscriminatorValue(value = "livreur")
public class Livreur extends Utilisateur{
    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name="livreur_commune",
            joinColumns={@JoinColumn(name="livreur_id")},
            inverseJoinColumns={@JoinColumn(name="commune_id")})
    private Set<Commune> communes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Livreur)) return false;
        if (!super.equals(o)) return false;
        Livreur livreur = (Livreur) o;
        return Objects.equals(communes, livreur.communes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), communes);
    }
}
