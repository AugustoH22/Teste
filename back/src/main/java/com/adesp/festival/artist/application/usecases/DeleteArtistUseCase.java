package com.adesp.festival.artist.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.artist.domain.entities.Artist;
import com.adesp.festival.artist.domain.exceptions.NotFoundArtistException;
import com.adesp.festival.artist.domain.repositories.ArtistRepository;



@UseCase
public class DeleteArtistUseCase {
    private final ArtistRepository artistRepository;

    public DeleteArtistUseCase(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public void execute(Long id){
        Artist artist = this.artistRepository.findById(id)
                .orElseThrow(() -> new NotFoundArtistException());

        this.artistRepository.deleteArtistById(id);
    }
}
