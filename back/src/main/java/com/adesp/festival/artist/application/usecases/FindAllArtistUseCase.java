package com.adesp.festival.artist.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.artist.domain.entities.Artist;
import com.adesp.festival.artist.domain.repositories.ArtistRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@UseCase
public class FindAllArtistUseCase {
    public final ArtistRepository artistRepository;

    public FindAllArtistUseCase(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<Artist> execute(){
        return this.artistRepository.findAll();
    }

    public Page<Artist> execute(Integer page, Integer items){
        return this.artistRepository.findAll(PageRequest.of(page, items));
    }
}
