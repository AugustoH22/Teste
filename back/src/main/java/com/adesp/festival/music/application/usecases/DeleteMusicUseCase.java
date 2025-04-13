package com.adesp.festival.music.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.music.application.services.MusicService;
import com.adesp.festival.music.domain.repositories.MusicRepository;

@UseCase
public class DeleteMusicUseCase {
    private final MusicRepository musicRepository;
    private final MusicService musicService;

    public DeleteMusicUseCase(MusicRepository musicRepository, MusicService musicService) {
        this.musicRepository = musicRepository;
        this.musicService = musicService;
    }

    public void execute(Long id){
        this.musicService.musicExists(id);
        this.musicRepository.deleteMusicById(id);
    }
}
