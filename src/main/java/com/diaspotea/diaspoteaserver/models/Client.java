package com.diaspotea.diaspoteaserver.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property ="id")
public class Client {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String numeroTel;
    private String mail;
    private String adresse;
    private String codePostale;
    private String ville;
    private int etage;
    @OneToMany(mappedBy = "client")
    private List<Commande>commandes;
    @OneToOne
    @JoinColumn(name = "utilisateur_ID")
    private Utilisateur utilisateur;
    @OneToMany(mappedBy = "client")
    private List<Panier> paniers;
}
