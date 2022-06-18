package com.diaspotea.diaspoteaserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class Commande {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    private LocalDateTime dateCommande;
    private String adresse;
    private String codePostale;
    private String ville;
    private String etage;
    private LocalDateTime dateLivraison;
    @ManyToOne
    @JoinColumn(name="livreur_id")
    private Livreur livreur;
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    @OneToMany(mappedBy = "commande",cascade = CascadeType.ALL)
    private List<LigneDeCommande>ligneDeCommandes;
}