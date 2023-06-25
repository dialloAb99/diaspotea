package com.diaspotea.diaspoteaserver.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
// il génère un constructeur avec tous les attribut de la classe

@AllArgsConstructor
//il génère les mutateurs des attribut de la classe
@Setter
// il génère les attribut de la classe
@Getter
// il spécifie que la classe est une table de la base
@Entity
// ça remplace la classe par son id quand l'objet est un attribut d'un autre objet
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,property ="id")
// ça permet de différencier le type dans la table utilisateur
@DiscriminatorValue(value = "admin")
public class Admin extends  Utilisateur {
}
