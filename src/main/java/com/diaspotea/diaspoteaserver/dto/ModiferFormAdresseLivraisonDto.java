package com.diaspotea.diaspoteaserver.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
public class ModiferFormAdresseLivraisonDto implements Serializable {
    private     Integer clientId;
    @NotBlank(message = "l'adresse est obligatoire")
    @Size(max=300, message = "l'adresse ne dois pas dépasser 300 caractère")
    private  String adresse;
    private Boolean memeAdresse;
    @NotBlank(message = "le code postale est obligatoire")
    @Size(max= 5,min =5, message = "le code postale dois comporter maximum 5 caractère")
    private  String codePostale;
    @NotBlank(message = "la ville est obligatoire")
    @Size(max=300, message = "l'adresse ne dois pas dépasser 300 caractère")
    private  String ville;
    @NotBlank(message = "l'étage est obligatoire")
    @Size(max = 75,min=1, message = "l'etage dois comporter minimum 75 caractère")
    private  String etage;



    public ModiferFormAdresseLivraisonDto(Integer clientId, String adresse, String codePostale, String ville, String etage, Boolean memeAdresse) {
        this.clientId = clientId;
        this.adresse = adresse;
        this.memeAdresse=memeAdresse;
        this.codePostale = codePostale;
        this.ville = ville;
        this.etage = etage;
    }
}
