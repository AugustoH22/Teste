package com.adesp.festival.artist.application.services;

import com.adesp.festival.artist.domain.entities.Artist;
import com.adesp.festival.artist.domain.exceptions.ArtistCpfException;
import com.adesp.festival.artist.domain.exceptions.ArtistNameException;
import com.adesp.festival.artist.domain.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Boolean isNameUsed(String name){
        Optional<Artist> storedArtist = this.artistRepository.findByNameAndStatus(name);

        if(storedArtist.isPresent()){
            throw new ArtistNameException();
        }

        return false;
    }

    public Boolean isNameUsed(Long id, String name){
        Optional<Artist> storedArtist = this.artistRepository.findByNameAndStatus(name);

        if(storedArtist.isPresent()){
            if(storedArtist.get().getId() != id){
                throw new ArtistNameException();
            }
        }

        return false;
    }

    public Boolean isCpfUsed(String cpf){
        Optional<Artist> storedArtist = this.artistRepository.findByCpfAndStatus(cpf);

        if(storedArtist.isPresent()){
            throw new ArtistCpfException();
        }

        return false;
    }

    public Boolean isCpfUsed(Long id, String cpf){
        Optional<Artist> storedArtist = this.artistRepository.findByCpfAndStatus(cpf);

        if(storedArtist.isPresent()){
            if(storedArtist.get().getId() != id){
                throw new ArtistCpfException();
            }
        }

        return false;
    }

}
