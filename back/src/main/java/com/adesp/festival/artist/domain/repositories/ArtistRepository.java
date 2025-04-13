package com.adesp.festival.artist.domain.repositories;

import com.adesp.festival.artist.domain.entities.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query(value = "SELECT * FROM artist WHERE is_active = TRUE AND name = ?1", nativeQuery = true)
    Optional<Artist> findByNameAndStatus(String name);

    @Query(value = "SELECT * FROM artist WHERE id = ?1", nativeQuery = true)
    Optional<Artist> findById(Long id);

    @Query(value = "SELECT * FROM artist WHERE is_active = TRUE AND cpf = ?1", nativeQuery = true)
    Optional<Artist> findByCpfAndStatus(String cpf);

    @Query(value = "SELECT * FROM artist WHERE is_active = TRUE", nativeQuery = true)
    List<Artist> findAllActive();

    @Query(value = "SELECT * FROM artist WHERE is_active = TRUE", nativeQuery = true)
    Page<Artist> findAllActive(Pageable pagination);

    @Query(value = "SELECT * FROM artist WHERE is_active = FALSE", nativeQuery = true)
    List<Artist> findAllInactive();

    @Query(value = "SELECT * FROM artist WHERE is_active = FALSE", nativeQuery = true)
    Page<Artist> findAllInactive(Pageable pagination);

    @Modifying
    @Transactional
    @Query(value = "UPDATE artist SET is_deleted = TRUE WHERE id = ?1", nativeQuery = true)
    void deleteArtistById(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE artist SET is_active = FALSE WHERE id = ?1", nativeQuery = true)
    void inactivateArtistById(Long id);

}

