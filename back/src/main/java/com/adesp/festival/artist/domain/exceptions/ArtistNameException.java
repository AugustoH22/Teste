package com.adesp.festival.artist.domain.exceptions;

public class ArtistNameException extends RuntimeException{
    public ArtistNameException() {
        super("Já existe um Artista ativo com o mesmo nome!");
    }
}
