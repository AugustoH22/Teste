package com.adesp.festival.music.application.services;

import com.adesp.festival.artist.domain.entities.Artist;
import com.adesp.festival.artist.domain.exceptions.NotFoundArtistException;
import com.adesp.festival.artist.domain.repositories.ArtistRepository;
import com.adesp.festival.music.domain.entities.Music;
import com.adesp.festival.music.domain.exceptions.MusicTitleException;
import com.adesp.festival.music.domain.exceptions.NotFoundMusicException;
import com.adesp.festival.music.domain.repositories.MusicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MusicService {

    private final MusicRepository musicRepository;
    private final ArtistRepository artistRepository;

    public MusicService(MusicRepository musicRepository, ArtistRepository artistRepository) {
        this.musicRepository = musicRepository;
        this.artistRepository = artistRepository;
    }

    @Transactional(readOnly = true)
    public Boolean isTitleUsedByArtists(String title, Long composerId, Long interpreterId){
        Optional<Music> storesMusic = this.musicRepository.findActiveMusicsInterpreterByTitle(title, composerId, interpreterId);

        if(storesMusic.isPresent()){
            throw new MusicTitleException();
        }

        return false;
    }

    @Transactional(readOnly = true)
    public Boolean isTitleUsedByArtists(Long id, String title, Long composerId, Long interpreterId){
        Optional<Music> storedMusic = this.musicRepository.findActiveMusicsInterpreterByTitle(title, composerId, interpreterId);

        if(storedMusic.isPresent()){
            if(storedMusic.get().getId() != id){
                throw new MusicTitleException();
            }
        }

        return false;
    }

    @Transactional(readOnly = true)
    public Boolean musicExists(Long id){
        Music storedMusic = this.musicRepository.findById(id)
                .orElseThrow(() -> new NotFoundMusicException());

        return true;
    }

    @Transactional(readOnly = true)
    public Boolean artistsExists(Long composerId, Long interpreterId){

        Artist storedComposer = this.artistRepository.findById(composerId)
                .orElseThrow(() -> new NotFoundArtistException());

        Artist storedInterpreter = this.artistRepository.findById(interpreterId)
                .orElseThrow(() -> new NotFoundArtistException());

        return true;
    }

}
