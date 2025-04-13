package com.adesp.festival.restaurant.application.dtos.response;

import com.adesp.festival.restaurant.domain.enums.Category;

import java.sql.Timestamp;

public record CreateRestaurantResponse(
        Long id,
        String name,
        String responsibleName,
        String cep,
        String address,
        String neighborhood,
        String number,
        Boolean isActive,
        Category category,
        Long createdBy,
        Timestamp createdAt
) {
}
