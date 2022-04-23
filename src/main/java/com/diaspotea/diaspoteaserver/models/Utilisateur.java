package com.diaspotea.diaspoteaserver.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.IDENTITY;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property ="id")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    private int id;
    private String userName;
    private String prenom;
    private String role;
    private  String modePasse;

    @OneToOne(mappedBy ="utilisateur")
    @Nullable
    private Client client;
    @OneToOne
    private Livreur livreur;


}
