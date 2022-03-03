package com.diaspotea.diaspoteaserver.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Secteur {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    private String name;
    private String adresse;
    @ManyToMany(mappedBy = "secteurs")
    private List<Livreur>livreurs;
}
