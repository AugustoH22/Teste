package com.adesp.festival.restaurant.application.dtos.request;

import com.adesp.festival.restaurant.domain.enums.Category;

public record CreateRestaurantRequest(
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
