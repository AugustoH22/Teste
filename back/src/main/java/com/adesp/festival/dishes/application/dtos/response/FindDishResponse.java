package com.adesp.festival.dishes.application.dtos.response;

import java.sql.Timestamp;

public record FindDishResponse(
        String name,
        Long restaurant,
        Boolean isActive,
        Long createdBy,
        Timestamp createdAt
) {
}
