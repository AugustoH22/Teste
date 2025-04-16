package com.adesp.festival.voting.domain.exceptions;

public class CPFHasAlreadyVotedForDishException extends RuntimeException {
    public CPFHasAlreadyVotedForDishException() {
        super("Ei, você já votou nesse prato!");
    }
}
