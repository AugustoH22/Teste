package com.adesp.festival.dishes.application.dtos.response;

import java.sql.Timestamp;

public record UpdateDishResponse(
        String name,
        Long restaurant,
        Boolean isActive,
        Long createdBy,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
