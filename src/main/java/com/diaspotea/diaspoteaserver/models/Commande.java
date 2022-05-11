package com.diaspotea.diaspoteaserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class Commande {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    private Date dateCommande;
    private String adresseLivraison;
    private Date dateLivraison;
    @ManyToOne
    @JoinColumn(name="livreur_id")
    private Livreur livreur;
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    @OneToMany(mappedBy = "commande")
    private List<LigneDeCommandeProduit>ligneDeCommandes;
}