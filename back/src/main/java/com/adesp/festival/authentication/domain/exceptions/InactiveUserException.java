package com.adesp.festival.authentication.domain.exceptions;

public class InactiveUserException extends RuntimeException {
    public InactiveUserException() {
        super("O seu usuário está inativo! Entre em contato com um administrador para realizar a ativação.");
    }
}
