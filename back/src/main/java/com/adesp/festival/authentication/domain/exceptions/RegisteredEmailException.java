package com.adesp.festival.authentication.domain.exceptions;

public class RegisteredEmailException extends RuntimeException {
    public RegisteredEmailException() {
        super("Já há um usuário para este e-mail");
    }
}
