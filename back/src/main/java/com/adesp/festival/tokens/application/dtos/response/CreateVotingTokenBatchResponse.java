package com.adesp.festival.tokens.application.dtos.response;

import java.sql.Timestamp;

public record CreateVotingTokenBatchResponse(
        Long id,
        Integer quantity,
        Long dishId,
        Long createdBy,
        Boolean isActive,
        Boolean isDeleted,
        Timestamp createdAt
) {
}
