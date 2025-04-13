package com.adesp.festival.restaurant.domain.exceptions;

public class NotFoundRestaurantException extends RuntimeException {
    public NotFoundRestaurantException() {
        super("O Restaurante não foi encontrado!");
    }
}
