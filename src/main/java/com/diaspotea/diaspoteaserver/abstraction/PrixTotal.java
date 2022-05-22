package com.diaspotea.diaspoteaserver.abstraction;

import com.diaspotea.diaspoteaserver.models.LigneDeCommande;

import java.util.List;

public interface PrixTotal {
     default float calculePrixTotal(List<LigneDeCommande> ligneDeCommandes) {
        float prixTotal=0.0F;
        for(LigneDeCommande ligneDeCommande :ligneDeCommandes) {
            prixTotal +=  ligneDeCommande.getPrix() * ligneDeCommande.getQuantiter();
        }
        return  prixTotal;
    }
}
