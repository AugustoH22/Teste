package com.adesp.festival.voting.application.dtos.request;

public record CreateDishVoteRequest(
        String name,
        String cpf,
        String contactNumber,
        Float presentation,
        Float treatment,
        Float creativity,
        Float originality,
        Float flavor,
        String votingToken
) {
}
