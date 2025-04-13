package com.adesp.festival.artist.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.artist.domain.entities.Artist;
import com.adesp.festival.artist.domain.repositories.ArtistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@UseCase
public class FindActiveArtistUseCase {

    private final ArtistRepository artistRepository;

    public FindActiveArtistUseCase(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> execute(){
        return this.artistRepository.findAllActive();
    }

    public Page<Artist> execute(Integer page, Integer items){
        return this.artistRepository.findAllActive(PageRequest.of(page, items));
    }
}
