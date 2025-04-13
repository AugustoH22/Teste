package com.adesp.festival.authentication.domain.exceptions;

public class ExpiredSessionException extends RuntimeException{
    public ExpiredSessionException() {
        super("A sessão está expirada!");
    }
}
