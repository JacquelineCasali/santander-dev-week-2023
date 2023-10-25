package com.java.dto;

import com.java.domain.model.Card;

import java.math.BigDecimal;

public record CardDto(
        Long id,
        String number,
        BigDecimal limit
) {
    public CardDto(Card cartao) {
        this (cartao.getId(),

                cartao.getNumber() ,
                cartao.getLimit());
    }
    public Card toCartao(){
        Card cartao = new Card();
        cartao.setId(this.id);
        cartao.setNumber(this.number);
        cartao.setLimit(this.limit);
        return cartao;
    }
}
