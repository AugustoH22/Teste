package com.adesp.festival.authentication.domain.exceptions;

public class NotFoundSessionException extends RuntimeException {
    public NotFoundSessionException() {
        super("A sessão buscada não foi encontrada!");
    }
}
