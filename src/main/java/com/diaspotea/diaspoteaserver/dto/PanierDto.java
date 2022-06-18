package com.diaspotea.diaspoteaserver.dto;

import com.diaspotea.diaspoteaserver.abstraction.PrixTotal;
import com.diaspotea.diaspoteaserver.models.LigneDeCommande;
import com.diaspotea.diaspoteaserver.models.Utilisateur;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PanierDto   implements Serializable, PrixTotal {
    private final int id;
    private final List<LigneDeCommande> ligneDeCommandes;
    private final Utilisateur client;


    public PanierDto(int id, List<LigneDeCommande> ligneDeCommandes, Utilisateur client) {
        this.id = id;
        this.ligneDeCommandes = ligneDeCommandes;
        this.client = client;
    }




}
