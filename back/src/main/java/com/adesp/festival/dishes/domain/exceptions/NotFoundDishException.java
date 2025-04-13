package com.adesp.festival.dishes.domain.exceptions;

public class NotFoundDishException extends RuntimeException {
    public NotFoundDishException() {
        super("O Prato não foi encontrado!");
    }
}
