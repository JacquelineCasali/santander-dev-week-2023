package com.java.dto;

import com.java.domain.model.User;

import java.util.List;
import static java.util.Optional.ofNullable;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
public record UserDto(
        Long id,
        String name,
        AccountDto account,
        CardDto card,
        List<FeatureDto> features,
        List<NewsDto> news) {
    public UserDto(User usuario){
        this(
                usuario.getId(),
                usuario.getName(),
                ofNullable(usuario.getAccount()).map(AccountDto::new).orElse(null),
                ofNullable(usuario.getCard()).map(CardDto::new).orElse(null),
                ofNullable(usuario.getFeatures()).orElse(emptyList()).stream().map(FeatureDto::new).collect(toList()),
                ofNullable(usuario.getNews()).orElse(emptyList()).stream().map(NewsDto::new).collect(toList()));

    }
    public User toUsuario() {
        User usuario = new User();
        usuario.setId(this.id);
        usuario.setName(this.name);
        usuario.setAccount(ofNullable(this.account).map(AccountDto::toConta).orElse(null));
        usuario.setCard(ofNullable(this.card).map(CardDto::toCartao).orElse(null));
        usuario.setFeatures(ofNullable(this.features).orElse(emptyList()).stream().map(FeatureDto::toRecurso).collect(toList()));
        usuario.setNews(ofNullable(this.news).orElse(emptyList()).stream().map(NewsDto::toNoticias).collect(toList()));
        return usuario;
    }
}
