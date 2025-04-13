package com.adesp.festival.music.domain.repositories;

import com.adesp.festival.music.domain.entities.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

    @Query(value = "SELECT * FROM musics WHERE title = ?1 AND composer_id = ?2 AND interpreter_id = ?3 AND is_active = TRUE", nativeQuery = true)
    Optional<Music> findActiveMusicsInterpreterByTitle(String title, Long composerId, Long interpreterId);

    @Modifying
    @Query(value = "UPDATE musics SET is_deleted = TRUE WHERE id = ?1", nativeQuery = true)
    void deleteMusicById(Long id);

    @Query(value = "SELECT * FROM musics WHERE is_active = TRUE", nativeQuery = true)
    List<Music> findActiveMusics();

    @Query(value = "SELECT * FROM musics WHERE is_active = TRUE", nativeQuery = true)
    Page<Music> findActiveMusic(Pageable pagination);

    @Query(value = "SELECT * FROM musics WHERE (coposer_id = ?1 OR interpreter_id = ?2) AND is_active = TRUE", nativeQuery = true)
    List<Music> findActiveMusicsByArtist(Long artistId);

    @Query(value = "SELECT * FROM musics WHERE (coposer_id = ?2 OR interpreter_id = ?2) AND is_active = TRUE", nativeQuery = true)
    Page<Music> findActiveMusicsByArtist(Pageable pagination, Long artistId);
}
