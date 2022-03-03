package com.diaspotea.diaspoteaserver.models;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Livreur {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    private String name;
    private String prenom;
    private String adresse;
    @ManyToMany
    private List<Secteur>secteurs;
}
