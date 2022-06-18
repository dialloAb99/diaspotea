package com.diaspotea.diaspoteaserver.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property ="id")
@DiscriminatorValue(value = "client")
public class Client extends Utilisateur {
    @OneToMany(mappedBy = "client",cascade = {CascadeType.ALL})
    private List<Commande>commandes;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(commandes, client.commandes)  && Objects.equals(paniers, client.paniers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), commandes, paniers);
    }

    @OneToMany(mappedBy = "client",cascade = {CascadeType.ALL})
    private List<Panier> paniers;

}
