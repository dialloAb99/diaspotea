package com.diaspotea.diaspoteaserver.dto;

import com.diaspotea.diaspoteaserver.abstraction.PrixTotal;
import com.diaspotea.diaspoteaserver.models.LigneDeCommande;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PaiementDto implements Serializable, PrixTotal {
    private final Integer clientId;
    @NotBlank(message = "l'adresse est obligatoire")
    @Size(max=300, message = "l'adresse ne dois pas dépasser 300 caractère")
    private   String adresse;
    private  Boolean memeAdresse;
    @NotBlank(message = "le code postale est obligatoire")
    @Size(max= 5,min =5, message = "le code postale dois comporter maximum 5 caractère")
    private   String codePostale;
    @NotBlank(message = "la ville est obligatoire")
    @Size(max=300, message = "l'adresse ne dois pas dépasser 300 caractère")
    private    String ville;
    @NotBlank(message = "l'étage est obligatoire")
    @Size(max = 75,min=1, message = "l'etage dois comporter minimum 75 caractère")
    private   String etage;
    @NotNull()
    private final Integer panierId;

    private final List<LigneDeCommande> ligneDeCommandes;
    @DecimalMin(value = "0.5")
    private final Float prixTotal;
    public PaiementDto(Integer clientId, String adresse, Boolean memeAdresse, String codePostale, String ville, String etage, Integer panierId, List<LigneDeCommande> ligneDeCommandes, Float prixTotal) {
        this.clientId = clientId;
        this.adresse = adresse;
        this.memeAdresse = memeAdresse;
        this.codePostale = codePostale;
        this.ville = ville;
        this.etage = etage;
        this.panierId = panierId;
        this.ligneDeCommandes = ligneDeCommandes;
        this.prixTotal = prixTotal;
    }
}
