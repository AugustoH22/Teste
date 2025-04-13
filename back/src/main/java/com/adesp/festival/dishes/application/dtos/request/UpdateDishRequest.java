package com.adesp.festival.dishes.application.dtos.request;

public record UpdateDishRequest(
        String name,
        Long restaurant,
        Boolean isActive
) {
}
