package com.adesp.festival.artist.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.artist.domain.entities.Artist;
import com.adesp.festival.artist.domain.exceptions.NotFoundArtistException;
import com.adesp.festival.artist.domain.repositories.ArtistRepository;


@UseCase
public class FindArtistByCpfUseCase {
    private final ArtistRepository artistRepository;

    public FindArtistByCpfUseCase(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist execute(String cpf){
        return this.artistRepository.findByCpfAndStatus(cpf)
                .orElseThrow(NotFoundArtistException::new);
    }
}
