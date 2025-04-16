package com.adesp.festival.tokens.domain.exceptions;

public class NotFoundVotingTokenBatchException extends RuntimeException{
    public NotFoundVotingTokenBatchException() {
        super("O Lote de Tokens de Votação não foi encontrado!");
    }
}
