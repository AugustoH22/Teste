package com.adesp.festival.authentication.domain.exceptions;

public class UnavailableEmailException extends RuntimeException {
    public UnavailableEmailException() {
        super("Este E-mail está indisponível!");
    }
}
