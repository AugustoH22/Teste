package com.adesp.festival.music.infrastructure.controller;

import com.adesp.festival.music.application.dtos.request.CreateMusicRequest;
import com.adesp.festival.music.application.dtos.request.UpdateMusicRequest;
import com.adesp.festival.music.application.dtos.response.CreateMusicResponse;
import com.adesp.festival.music.application.dtos.response.DeleteMusicResponse;
import com.adesp.festival.music.application.dtos.response.FindMusicResponse;
import com.adesp.festival.music.application.dtos.response.UpdateMusicResponse;
import com.adesp.festival.music.application.usecases.*;
import com.adesp.festival.music.domain.entities.Music;
import com.adesp.festival.music.infrastructure.mappers.MusicMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MusicController {

    private final CreateMusicUseCase createMusicUseCase;
    private final UpdateMusicUseCase updateMusicUseCase;
    private final DeleteMusicUseCase deleteMusicUseCase;
    private final FindMusicUseCase findMusicUseCase;
    private final FindActiveMusicUseCase findActiveMusicUseCase;
    private final FindAllMusicUseCase findAllMusicUseCase;
    private final MusicMapper musicMapper;
    private final FindMusicsByArtistUseCase findMusicsByArtistUseCase;

    public MusicController(CreateMusicUseCase createMusicUseCase, UpdateMusicUseCase updateMusicUseCase, DeleteMusicUseCase deleteMusicUseCase, FindMusicUseCase findMusicUseCase, FindActiveMusicUseCase findActiveMusicUseCase, FindAllMusicUseCase findAllMusicUseCase, MusicMapper musicMapper,FindMusicsByArtistUseCase findMusicsByArtistUseCase) {
        this.createMusicUseCase = createMusicUseCase;
        this.updateMusicUseCase = updateMusicUseCase;
        this.deleteMusicUseCase = deleteMusicUseCase;
        this.findMusicUseCase = findMusicUseCase;
        this.findActiveMusicUseCase = findActiveMusicUseCase;
        this.findAllMusicUseCase = findAllMusicUseCase;
        this.musicMapper = musicMapper;
        this.findMusicsByArtistUseCase = findMusicsByArtistUseCase;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/v1/music")
    public ResponseEntity<CreateMusicResponse> post(@RequestBody CreateMusicRequest createMusicRequest){
        Music music = this.musicMapper.createMusicRequestToDomain(createMusicRequest);
        Music storedMusic = this.createMusicUseCase.execute(music);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.musicMapper.domainToCreateMusicResponse(storedMusic));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/v1/music/{id}")
    public ResponseEntity<UpdateMusicResponse> put(@PathVariable Long id, @RequestBody UpdateMusicRequest updateMusicRequest){
        Music music = this.musicMapper.updateMusicRequestToDomain(updateMusicRequest);
        Music storedMusic = this.updateMusicUseCase.execute(id, music);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.musicMapper.domainToUpdateMusicResponse(storedMusic));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/v1/music/{id}")
    public ResponseEntity<DeleteMusicResponse> delete(@PathVariable Long id){
        this.deleteMusicUseCase.execute(id);
        return ResponseEntity.ok(new DeleteMusicResponse("A musica foi excluída com sucesso!"));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICAL_JUDGE')")
    @GetMapping("/v1/music/all")
    public ResponseEntity<List<FindMusicResponse>> getAll(){
        List<Music> musics = this.findAllMusicUseCase.execute();
        List<FindMusicResponse> musicResponses = musics.stream()
                .map(this.musicMapper::domainToFindMusicResponse)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(musicResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICAL_JUDGE')")
    @GetMapping("/v1/music/all/paginate")
    public ResponseEntity<Page<FindMusicResponse>> getAllPaginate(@RequestParam Integer page, @RequestParam Integer items){
        Page<Music> musics = this.findAllMusicUseCase.execute(page, items);
        Page<FindMusicResponse> musicResponses = musics
                .map(this.musicMapper::domainToFindMusicResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(musicResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICAL_JUDGE')")
    @GetMapping("/v1/music")
    public ResponseEntity<List<FindMusicResponse>> getAllActive(){
        List<Music> musics = this.findActiveMusicUseCase.execute();
        List<FindMusicResponse> musicResponses = musics.stream()
                .map(this.musicMapper::domainToFindMusicResponse)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(musicResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICAL_JUDGE')")
    @GetMapping("/v1/music/paginate")
    public ResponseEntity<Page<FindMusicResponse>> getAllActivePaginate(@RequestParam Integer page, @RequestParam Integer items){
        Page<Music> musics = this.findActiveMusicUseCase.execute(page, items);
        Page<FindMusicResponse> musicResponses = musics
                .map(this.musicMapper::domainToFindMusicResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(musicResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICAL_JUDGE')")
    @GetMapping("v1/music/{id}")
    public ResponseEntity<FindMusicResponse> getById(@PathVariable Long id){
        Music music = this.findMusicUseCase.execute(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.musicMapper.domainToFindMusicResponse(music));
    }




    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/v1/music/artist/{id}")
    public ResponseEntity<List<FindMusicResponse>> getAllByArtist(@PathVariable Long id){
        List<Music> musics = this.findMusicsByArtistUseCase.execute(id);
        List<FindMusicResponse> musicResponses = musics.stream()
                .map(this.musicMapper::domainToFindMusicResponse)
                .toList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(musicResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/v1/music/artist/{id}/paginate")
    public ResponseEntity<Page<FindMusicResponse>> getAllByArtistPaginate(@RequestParam Integer page, @RequestParam Integer items, @PathVariable Long id){
        Page<Music> musics = this.findMusicsByArtistUseCase.execute(page, items, id);
        Page<FindMusicResponse> musicResponses = musics
                .map(this.musicMapper::domainToFindMusicResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(musicResponses);
    }
}
