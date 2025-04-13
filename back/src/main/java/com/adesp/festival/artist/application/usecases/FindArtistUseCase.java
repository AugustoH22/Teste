package com.adesp.festival.artist.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.artist.domain.entities.Artist;
import com.adesp.festival.artist.domain.exceptions.NotFoundArtistException;
import com.adesp.festival.artist.domain.repositories.ArtistRepository;



@UseCase
public class FindArtistUseCase {
    private final ArtistRepository artistRepository;

    public FindArtistUseCase(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist execute(Long id){
        return this.artistRepository.findById(id)
                .orElseThrow(NotFoundArtistException::new);
    }
}
