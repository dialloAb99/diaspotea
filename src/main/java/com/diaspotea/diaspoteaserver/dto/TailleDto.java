package com.diaspotea.diaspoteaserver.dto;

import com.diaspotea.diaspoteaserver.models.Taille;
import lombok.Data;

import java.io.Serializable;

@Data
public class TailleDto implements Serializable {
    private final int id;
    private final String name;


}
