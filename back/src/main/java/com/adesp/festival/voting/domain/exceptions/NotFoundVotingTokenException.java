package com.adesp.festival.voting.domain.exceptions;

public class NotFoundVotingTokenException extends RuntimeException {
    public NotFoundVotingTokenException() {
        super("O Token de Votação não foi encontrado!");
    }
}
