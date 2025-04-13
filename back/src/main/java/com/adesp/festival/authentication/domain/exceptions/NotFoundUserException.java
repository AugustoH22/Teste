package com.adesp.festival.authentication.domain.exceptions;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException() {
        super("O Usuário não foi encontrado!");
    }
}
