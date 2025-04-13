package com.adesp.festival.authentication.domain.exceptions;

public class InvalidInviteException extends RuntimeException {
    public InvalidInviteException() {
        super("O Convite é invalido!");
    }
}
