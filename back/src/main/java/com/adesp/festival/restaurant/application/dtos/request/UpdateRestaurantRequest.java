package com.adesp.festival.restaurant.application.dtos.request;

import com.adesp.festival.restaurant.domain.enums.Category;

import java.sql.Timestamp;

public record UpdateRestaurantRequest(
        String name,
        String responsibleName,
        String cep,
        String address,
        String neighborhood,
        String number,
        Boolean isActive,
        Category category
) {
}
