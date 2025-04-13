package com.adesp.festival.music.infrastructure.mappers;

import com.adesp.festival.artist.domain.entities.Artist;
import com.adesp.festival.music.application.dtos.request.CreateMusicRequest;
import com.adesp.festival.music.application.dtos.request.UpdateMusicRequest;
import com.adesp.festival.music.application.dtos.response.CreateMusicResponse;
import com.adesp.festival.music.application.dtos.response.FindMusicResponse;
import com.adesp.festival.music.application.dtos.response.UpdateMusicResponse;
import com.adesp.festival.music.domain.entities.Music;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface MusicMapper {
    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "interpreterId", source = "interpreter", qualifiedByName = "mapInterpreterToLong")
    @Mapping(target = "composerId", source = "composer", qualifiedByName = "mapComposerToLong")
    CreateMusicResponse domainToCreateMusicResponse(Music music);

    @Mapping(target = "interpreter", source = "interpreterId", qualifiedByName = "mapLongToInterpreter")
    @Mapping(target = "composer", source = "composerId", qualifiedByName = "mapLongToComposer")
    Music createMusicRequestToDomain(CreateMusicRequest createMusicRequest);

    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "interpreterId", source = "interpreter", qualifiedByName = "mapInterpreterToLong")
    @Mapping(target = "composerId", source = "composer", qualifiedByName = "mapComposerToLong")
    UpdateMusicResponse domainToUpdateMusicResponse(Music music);

    @Mapping(target = "interpreter", source = "interpreterId", qualifiedByName = "mapLongToInterpreter")
    @Mapping(target = "composer", source = "composerId", qualifiedByName = "mapLongToComposer")
    Music updateMusicRequestToDomain(UpdateMusicRequest updateMusicRequest);

    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "interpreterId", source = "interpreter", qualifiedByName = "mapInterpreterToLong")
    @Mapping(target = "composerId", source = "composer", qualifiedByName = "mapComposerToLong")
    FindMusicResponse domainToFindMusicResponse(Music music);

    @Named("mapInterpreterToLong")
    default Long mapInterpreterToLong(Artist artist){
        return artist.getId();
    }

    @Named("mapLongToInterpreter")
    default Artist mapLongToInterpreter(Long id){
        return new Artist(id);
    }

    @Named("mapComposerToLong")
    default Long mapComposerToLong(Artist artist){
        return artist.getId();
    }

    @Named("mapLongToComposer")
    default Artist mapLongToComposer(Long id){
        return new Artist(id);
    }
}
