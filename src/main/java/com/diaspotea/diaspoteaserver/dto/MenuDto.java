package com.diaspotea.diaspoteaserver.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuDto implements Serializable {
    private  final Integer id;
    private final String nom;
    private final String imageUrl;
    private final String description;
    private final float prix;
}
