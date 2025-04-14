package com.adesp.festival.voting.domain.exceptions;

public class InvalidVotingTokenException extends RuntimeException {
    public InvalidVotingTokenException() {
        super("O Token enviado não é válido para votação!");
    }
}
