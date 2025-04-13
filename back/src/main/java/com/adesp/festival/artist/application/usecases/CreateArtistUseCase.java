package com.adesp.festival.artist.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.artist.application.services.ArtistService;
import com.adesp.festival.artist.domain.entities.Artist;
import com.adesp.festival.artist.domain.repositories.ArtistRepository;


@UseCase
public class CreateArtistUseCase {

    private final ArtistRepository artistRepository;
    private final ArtistService artistService;

    public CreateArtistUseCase(ArtistRepository artistRepository, ArtistService artistService) {
        this.artistService = artistService;
        this.artistRepository = artistRepository;
    }

    public Artist execute(Artist artist){
        this.artistService.isNameUsed(artist.getName());
        return this.artistRepository.save(artist);
    }
}
