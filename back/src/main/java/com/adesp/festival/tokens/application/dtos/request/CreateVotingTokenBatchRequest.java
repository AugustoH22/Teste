package com.adesp.festival.tokens.application.dtos.request;

public record CreateVotingTokenBatchRequest(
    Integer quantity,
    Long dishId,
    Boolean isActive
) {
}
