package com.diaspotea.diaspoteaserver.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value="ligneDeCommandeMenu")
public class LigneDeCommandeMenu extends LigneDeCommande {
    @ManyToOne
    private Menu menu;
}