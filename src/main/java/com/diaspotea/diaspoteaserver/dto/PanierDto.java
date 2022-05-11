package com.diaspotea.diaspoteaserver.dto;

import com.diaspotea.diaspoteaserver.models.Client;
import com.diaspotea.diaspoteaserver.models.LigneDeCommande;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PanierDto implements Serializable {
    private final int id;
    private final List<LigneDeCommande> ligneDeCommandes;
    private final Client client;

    private float prixTotal;

    public PanierDto(int id, List<LigneDeCommande> ligneDeCommandes, Client client) {
        this.id = id;
        this.ligneDeCommandes = ligneDeCommandes;
        this.client = client;
       calculePrixTotal();
    }


  private void calculePrixTotal() {
        for(LigneDeCommande ligneDeCommande :ligneDeCommandes) {
            prixTotal +=  ligneDeCommande.getPrix() * ligneDeCommande.getQuantiter();
        }

    }

}
