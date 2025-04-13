package com.adesp.festival.restaurant.application.dtos.response;

import com.adesp.festival.restaurant.domain.enums.Category;

public record GetRestaurantResponse(
        Long id,
        String name,
        String responsibleName,
        String cep,
        String address,
        String neighborhood,
        String number,
        Long createdBy,
        Category category
) {
}
