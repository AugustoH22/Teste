package com.adesp.festival.authentication.domain.exceptions;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException() {
        super("As credenciais de acesso são inválidas! Verifique seus dados e tente novamente.");
    }
}
