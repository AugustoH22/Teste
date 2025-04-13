package com.adesp.festival.authentication.domain.exceptions;

public class NotFoundInviteException extends RuntimeException {
    public NotFoundInviteException() {
        super("Não foi encontrado nenhum convite válido!");
    }
}
