package com.adesp.festival.music.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.music.application.services.MusicService;
import com.adesp.festival.music.domain.entities.Music;
import com.adesp.festival.music.domain.exceptions.NotFoundMusicException;
import com.adesp.festival.music.domain.repositories.MusicRepository;
import org.springframework.transaction.annotation.Transactional;

@UseCase
public class UpdateMusicUseCase {

    private final MusicRepository musicRepository;
    private final MusicService musicService;

    public UpdateMusicUseCase(MusicRepository musicRepository, MusicService musicService) {
        this.musicRepository = musicRepository;
        this.musicService = musicService;
    }

    @Transactional
    public Music execute(Long id, Music toUpdate){
        this.musicService.isTitleUsedByArtists(id,toUpdate.getTitle(),toUpdate.getComposer().getId(), toUpdate.getInterpreter().getId());
        Music storedMusic = this.musicRepository.findById(id)
                .map(music -> {
                    this.musicService.musicExists(music.getId());
                    music.setTitle(toUpdate.getTitle());
                    music.setComposer(toUpdate.getComposer());
                    music.setInterpreter(toUpdate.getInterpreter());
                    music.setActive(toUpdate.getActive());

                    return music;
                })
                .orElseThrow(() -> new NotFoundMusicException());
        return this.musicRepository.save(storedMusic);
    }
}
