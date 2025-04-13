package com.adesp.festival.music.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.music.application.services.MusicService;
import com.adesp.festival.music.domain.entities.Music;
import com.adesp.festival.music.domain.repositories.MusicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@UseCase
public class FindMusicsByArtistUseCase {
    private final MusicRepository musicRepository;
    private final MusicService musicService;

    public FindMusicsByArtistUseCase(MusicRepository musicRepository, MusicService musicService) {
        this.musicRepository = musicRepository;
        this.musicService = musicService;
    }
    public List<Music> execute(Long id){
        return this.musicRepository.findActiveMusicsByArtist(id);
    }

    public Page<Music> execute(Integer page, Integer items, Long id){
        return this.musicRepository.findActiveMusicsByArtist(PageRequest.of(page, items), id);
    }
}
