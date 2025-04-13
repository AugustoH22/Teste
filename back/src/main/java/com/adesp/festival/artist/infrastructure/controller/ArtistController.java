package com.adesp.festival.artist.infrastructure.controller;

import com.adesp.festival.artist.application.dtos.request.CreateArtistRequest;
import com.adesp.festival.artist.application.dtos.request.UpdateArtistRequest;
import com.adesp.festival.artist.application.dtos.response.CreateArtistResponse;
import com.adesp.festival.artist.application.dtos.response.DeleteArtistResponse;
import com.adesp.festival.artist.application.dtos.response.GetArtistResponse;
import com.adesp.festival.artist.application.dtos.response.UpdateArtistResponse;
import com.adesp.festival.artist.application.usecases.*;
import com.adesp.festival.artist.domain.entities.Artist;
import com.adesp.festival.artist.infrastructure.mappers.ArtistMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArtistController {

    private final CreateArtistUseCase createArtistUseCase;
    private final UpdateArtistUseCase updateArtistUseCase;
    private final DeleteArtistUseCase deleteArtistUseCase;
    private final FindActiveArtistUseCase findActiveArtistUseCase;
    private final FindArtistUseCase findArtistUseCase;
    private final FindAllArtistUseCase findAllArtistUseCase;
    private final ArtistMapper artistMapper;
    private final FindArtistByCpfUseCase findArtistByCpfUseCase;

    public ArtistController(CreateArtistUseCase createArtistUseCase, UpdateArtistUseCase updateArtistUseCase, DeleteArtistUseCase deleteArtistUseCase, FindActiveArtistUseCase findActiveArtistUseCase, FindArtistUseCase findArtistUseCase, FindAllArtistUseCase findAllArtistUseCase, ArtistMapper artistMapper, FindArtistByCpfUseCase findArtistByCpfUseCase) {
        this.createArtistUseCase = createArtistUseCase;
        this.updateArtistUseCase = updateArtistUseCase;
        this.deleteArtistUseCase = deleteArtistUseCase;
        this.findActiveArtistUseCase = findActiveArtistUseCase;
        this.findArtistUseCase = findArtistUseCase;
        this.findAllArtistUseCase = findAllArtistUseCase;
        this.artistMapper = artistMapper;
        this.findArtistByCpfUseCase = findArtistByCpfUseCase;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/v1/artist")
    public ResponseEntity<CreateArtistResponse> post(@RequestBody CreateArtistRequest createArtistRequest, HttpServletRequest request){
        Artist artist = this.artistMapper.createArtistRequestToDomain(createArtistRequest);
        Artist storedArtist = this.createArtistUseCase.execute(artist);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.artistMapper.domainToCreateArtistResponse(storedArtist));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/v1/artist/{id}")
    public ResponseEntity<UpdateArtistResponse> put(@PathVariable Long id, @RequestBody UpdateArtistRequest updateArtistRequest){
        Artist artist = this.artistMapper.updateArtistRequestToDomain(updateArtistRequest);
        Artist storedArtist = this.updateArtistUseCase.execute(id, artist);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.artistMapper.domainToUpdateArtistResponse(storedArtist));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/v1/artist/{id}")
    public ResponseEntity<DeleteArtistResponse> delete(@PathVariable Long id){
        this.deleteArtistUseCase.execute(id);
        return ResponseEntity.ok(new DeleteArtistResponse("O Artista foi excluído com sucesso!"));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICAL_JUDGE')")
    @GetMapping("/v1/artist")
    public ResponseEntity<List<GetArtistResponse>> getAllActive(){
        List<GetArtistResponse> artistResponses = this.findActiveArtistUseCase.execute()
                .stream().map(this.artistMapper::domainToGetArtistResponse).toList();
        return ResponseEntity.ok(artistResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICAL_JUDGE')")
    @GetMapping("/v1/artist/paginate")
    public ResponseEntity<Page<GetArtistResponse>> getAllActivePaginate(@RequestParam Integer page, @RequestParam Integer items){
        Page<GetArtistResponse> artistResponses = this.findActiveArtistUseCase.execute(page, items)
                .map(this.artistMapper::domainToGetArtistResponse);
        return ResponseEntity.ok(artistResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICAL_JUDGE')")
    @GetMapping("/v1/artist/all")
    public ResponseEntity<List<GetArtistResponse>> getAll(){
        List<GetArtistResponse> artistResponses = this.findAllArtistUseCase.execute()
                .stream().map(this.artistMapper::domainToGetArtistResponse).toList();
        return ResponseEntity.ok(artistResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICAL_JUDGE')")
    @GetMapping("/v1/artist/all/paginate")
    public ResponseEntity<Page<GetArtistResponse>> getAll(@RequestParam Integer page, @RequestParam Integer items){
        Page<GetArtistResponse> artistResponses = this.findAllArtistUseCase.execute(page, items)
                .map(this.artistMapper::domainToGetArtistResponse);
        return ResponseEntity.ok(artistResponses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICAL_JUDGE')")
    @GetMapping("/v1/artist/{id}")
    public ResponseEntity<GetArtistResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(this.artistMapper.domainToGetArtistResponse(this.findArtistUseCase.execute(id)));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'MUSICAL_JUDGE')")
    @GetMapping("/v1/artist/{cpf}")
    public ResponseEntity<GetArtistResponse> getByCpf(@PathVariable String cpf){
        return ResponseEntity.ok(this.artistMapper.domainToGetArtistResponse(this.findArtistByCpfUseCase.execute(cpf)));
    }

}
