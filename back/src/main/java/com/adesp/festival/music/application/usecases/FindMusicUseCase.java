package com.adesp.festival.music.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.dishes.domain.exceptions.NotFoundDishException;
import com.adesp.festival.music.application.services.MusicService;
import com.adesp.festival.music.domain.entities.Music;
import com.adesp.festival.music.domain.repositories.MusicRepository;

@UseCase
public class FindMusicUseCase {
    private final MusicRepository musicRepository;
    private final MusicService musicService;

    public FindMusicUseCase(MusicRepository musicRepository, MusicService musicService) {
        this.musicRepository = musicRepository;
        this.musicService = musicService;
    }

    public Music execute(Long id){
        return this.musicRepository.findById(id)
                .orElseThrow(() -> new NotFoundDishException());
    }
}
