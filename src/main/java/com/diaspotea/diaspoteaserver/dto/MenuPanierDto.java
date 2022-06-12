package com.diaspotea.diaspoteaserver.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class MenuPanierDto {

    @NotNull(message = "Veuillez choisir une quantiter")
    private Integer quantiter;
    @NotNull(message = "menu inexistant")
        private Integer menuId;
}
