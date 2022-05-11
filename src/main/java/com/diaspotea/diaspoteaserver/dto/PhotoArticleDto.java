package com.diaspotea.diaspoteaserver.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public  class PhotoArticleDto implements Serializable {
    private final int id;
    private final String libele;
    private final String url;

    public PhotoArticleDto(int id, String libele, String url) {
        this.id = id;
        this.libele = libele;
        this.url = url;
    }
}