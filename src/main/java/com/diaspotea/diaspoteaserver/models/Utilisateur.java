package com.diaspotea.diaspoteaserver.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import java.util.Locale;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property ="id")
@DiscriminatorColumn(name = "TYPE",discriminatorType = DiscriminatorType.STRING)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    @Column(unique = true)
    private String nom;
    private String prenom;
    private String email;
    private  String modePasse;
    private String numTel;
    private String adresse;
    private String codePostale;
    private String ville;
    private String etage;
     public String  getRole(){
         return this.getClass().getSimpleName().toLowerCase(Locale.ROOT);
     }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilisateur)) return false;
        Utilisateur that = (Utilisateur) o;
        return id == that.id && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom)  && Objects.equals(email, that.email) && Objects.equals(modePasse, that.modePasse) && Objects.equals(numTel, that.numTel) && Objects.equals(adresse, that.adresse) && Objects.equals(codePostale, that.codePostale) && Objects.equals(ville, that.ville) && Objects.equals(etage, that.etage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, email, modePasse, numTel, adresse, codePostale, ville, etage);
    }
}
