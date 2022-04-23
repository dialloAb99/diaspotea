package com.diaspotea.diaspoteaserver.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
public class Livreur {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    @Column(name ="nom")
    private String name;
    private String prenom;
    private String adresse;
    @Column(name = "code_postale")
    private  String codePostale;
    @Column(name="num_tel")
    private  String numeroTelephone;
    @Column(name="ville")
    private  String ville;
    @OneToOne
    private  Utilisateur utilisateur;
    @ManyToMany
    @JoinTable(name="livreur_commune",
            joinColumns={@JoinColumn(name="livreur_id")},
            inverseJoinColumns={@JoinColumn(name="commune_id")})
    private Set<Commune> communes;
}
