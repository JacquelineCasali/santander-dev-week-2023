package com.java.dto;

import com.java.domain.model.Account;

import java.math.BigDecimal;

public record AccountDto(
        Long id,
        String number,
        String agency,
        BigDecimal balance,
         BigDecimal limit

) {
    public AccountDto(Account conta) {
        this(conta.getId(), conta.getNumber(), conta.getAgency(), conta.getBalance(), conta.getLimit());
    }
    public Account toConta(){
        Account conta= new Account();
        conta.setId(this.id);
        conta.setNumber(this.number);
        conta.setAgency(this.agency);
        conta.setBalance(this.balance);
        conta.setLimit(this.limit);
        return conta;
    }

}
