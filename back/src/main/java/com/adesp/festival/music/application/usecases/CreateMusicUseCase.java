package com.adesp.festival.music.application.usecases;

import com.adesp.festival.UseCase;

import com.adesp.festival.dishes.application.services.DishService;
import com.adesp.festival.dishes.domain.entities.Dish;
import com.adesp.festival.dishes.domain.repositories.DishRepository;
import com.adesp.festival.music.application.services.MusicService;
import com.adesp.festival.music.domain.entities.Music;
import com.adesp.festival.music.domain.repositories.MusicRepository;
import org.springframework.transaction.annotation.Transactional;

@UseCase
public class CreateMusicUseCase {

    private final MusicRepository musicRepository;
    private final MusicService musicService;

    public CreateMusicUseCase(MusicRepository musicRepository, MusicService musicService) {
        this.musicRepository = musicRepository;
        this.musicService = musicService;
    }

    @Transactional
    public Music execute(Music music){
        this.musicService.isTitleUsedByArtists(music.getTitle(), music.getComposer().getId(), music.getInterpreter().getId());
        this.musicService.artistsExists(music.getComposer().getId(), music.getInterpreter().getId());
        return this.musicRepository.save(music);
    }

}
