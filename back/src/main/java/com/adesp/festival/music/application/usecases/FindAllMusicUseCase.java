package com.adesp.festival.music.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.music.application.services.MusicService;
import com.adesp.festival.music.domain.entities.Music;
import com.adesp.festival.music.domain.repositories.MusicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@UseCase
public class FindAllMusicUseCase {
    private final MusicRepository musicRepository;
    private final MusicService musicService;

    public FindAllMusicUseCase(MusicRepository musicRepository, MusicService musicService) {
        this.musicRepository = musicRepository;
        this.musicService = musicService;
    }

    public List<Music> execute(){
        return this.musicRepository.findAll();
    }

    public Page<Music> execute(Integer page, Integer items){
        return this.musicRepository.findAll(PageRequest.of(page, items));
    }
}
