package com.adesp.festival.artist.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.artist.application.services.ArtistService;
import com.adesp.festival.artist.domain.entities.Artist;
import com.adesp.festival.artist.domain.exceptions.NotFoundArtistException;
import com.adesp.festival.artist.domain.repositories.ArtistRepository;



@UseCase
public class UpdateArtistUseCase {
    private final ArtistRepository artistRepository;
    private final ArtistService artistService;

    public UpdateArtistUseCase(ArtistRepository artistRepository, ArtistService artistService) {
        this.artistRepository = artistRepository;
        this.artistService = artistService;
    }

    public Artist execute(Long id, Artist toUpdate){
        Artist stored = this.artistRepository.findById(id)
                .map(artist -> {
                    artist.setName(toUpdate.getName());
                    this.artistService.isNameUsed(id, toUpdate.getName());
                    artist.setCpf(toUpdate.getCpf());
                    this.artistService.isCpfUsed(id, toUpdate.getCpf());
                    artist.setRg(toUpdate.getRg());
                    artist.setCity(toUpdate.getCity());
                    artist.setUf(toUpdate.getUf());
                    artist.setTelephone(toUpdate.getTelephone());
                    artist.setEmail(toUpdate.getEmail());
                    artist.setActive(toUpdate.getActive());

                    return artist;
                })
                .orElseThrow(() -> new NotFoundArtistException());

        return this.artistRepository.save(stored);
    }
}
