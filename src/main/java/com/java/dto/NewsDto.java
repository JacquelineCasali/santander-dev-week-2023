package com.java.dto;

import com.java.domain.model.News;

public record NewsDto(
     Long id,
     String icon,
     String descrition

) {
    public NewsDto(News noticias){
        this(noticias.getId(), noticias.getIcon(), noticias.getDescrition());
    }
    public News toNoticias(){
        News noticias = new News();
        noticias.setId(this.id);
        noticias.setIcon(this.icon);
        noticias.setDescrition(this.descrition);
        return  noticias;
    }
}
