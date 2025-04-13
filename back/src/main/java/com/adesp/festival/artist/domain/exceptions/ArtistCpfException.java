package com.adesp.festival.artist.domain.exceptions;

public class ArtistCpfException extends RuntimeException {
    public ArtistCpfException() {
        super("Já existe um Artista ativo com o mesmo CPF!");
    }
}
