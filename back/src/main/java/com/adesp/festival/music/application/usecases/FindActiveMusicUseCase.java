package com.adesp.festival.music.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.music.application.services.MusicService;
import com.adesp.festival.music.domain.entities.Music;
import com.adesp.festival.music.domain.repositories.MusicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@UseCase
public class FindActiveMusicUseCase {
    private final MusicRepository musicRepository;
    private final MusicService musicService;

    public FindActiveMusicUseCase(MusicRepository musicRepository, MusicService musicService) {
        this.musicRepository = musicRepository;
        this.musicService = musicService;
    }
    public List<Music> execute(){
        return this.musicRepository.findActiveMusics();
    }

    public Page<Music> execute(Integer page, Integer items){
        return this.musicRepository.findActiveMusic(PageRequest.of(page, items));
    }
}
