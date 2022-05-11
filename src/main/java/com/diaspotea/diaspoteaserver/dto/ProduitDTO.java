package com.diaspotea.diaspoteaserver.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProduitDTO {
    @NotNull
    private  Integer id;
    @NotNull(message = "Veuillez choisir une taille")
    private  Integer tailleID;
    @NotNull(message = "Veuillez choisir une quantiter")
    private  Integer quantiter;

    private Integer panierId;

}
