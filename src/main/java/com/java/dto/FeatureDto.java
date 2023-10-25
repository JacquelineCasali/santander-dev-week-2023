package com.java.dto;

import com.java.domain.model.Feature;

public record FeatureDto(Long id,
                         String icon,
                         String description) {
    public FeatureDto(Feature recurso){
        this(recurso.getId(), recurso.getIcon(), recurso.getDescrition());
    }

public  Feature toRecurso(){
Feature recurso = new Feature();
recurso.setId(this.id);
recurso.setIcon(this.icon);
recurso.setDescrition(this.description);
return recurso;
}
}
