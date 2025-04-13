package com.adesp.festival.dishes.application.dtos.request;

public record CreateDishRequest(
        String name,
        Long restaurant,
        Boolean isActive
) {
}
