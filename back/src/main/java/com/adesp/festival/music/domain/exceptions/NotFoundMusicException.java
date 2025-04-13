package com.adesp.festival.music.domain.exceptions;

public class NotFoundMusicException extends RuntimeException{
    public NotFoundMusicException() {
        super("A música não foi encontrada!");
    }
}
