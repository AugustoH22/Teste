package com.adesp.festival.restaurant.domain.exceptions;

public class RestaurantNameException extends RuntimeException {
    public RestaurantNameException() {
        super("Já existe um Restaurante ativo com o mesmo nome!");
    }
}
