package com.adesp.festival.artist.domain.exceptions;

public class NotFoundArtistException extends RuntimeException{
    public NotFoundArtistException() {
        super("O Artista não foi encontrado!");
    }
}
