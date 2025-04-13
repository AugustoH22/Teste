package com.adesp.festival.music.domain.exceptions;

public class MusicTitleException extends RuntimeException{
    public MusicTitleException() {
        super("Já existe uma Musica ativa com o mesmo Título para este Compositor e interprete!");
    }
}
