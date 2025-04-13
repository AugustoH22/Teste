package com.adesp.festival.authentication.domain.exceptions;

public class UnavailableUsernameException extends RuntimeException{
    public UnavailableUsernameException() {
        super("O Nome de Usuário está indisponível");
    }
}
