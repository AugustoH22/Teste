package com.adesp.festival.authentication.domain.exceptions;

public class ExpiredInviteException extends RuntimeException {
    public ExpiredInviteException() {
        super("O Convite expirou!");
    }
}
