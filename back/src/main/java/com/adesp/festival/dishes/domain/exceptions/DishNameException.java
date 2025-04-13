package com.adesp.festival.dishes.domain.exceptions;

public class DishNameException extends RuntimeException {
    public DishNameException() {
        super("Já existe um Prato ativo com o mesmo nome para este Restaurante!");
    }
}
