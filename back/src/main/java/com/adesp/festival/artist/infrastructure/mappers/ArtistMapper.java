package com.adesp.festival.artist.infrastructure.mappers;

import com.adesp.festival.artist.application.dtos.request.CreateArtistRequest;
import com.adesp.festival.artist.application.dtos.request.UpdateArtistRequest;
import com.adesp.festival.artist.application.dtos.response.CreateArtistResponse;
import com.adesp.festival.artist.application.dtos.response.GetArtistResponse;
import com.adesp.festival.artist.application.dtos.response.UpdateArtistResponse;
import com.adesp.festival.artist.domain.entities.Artist;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    @Mapping(target = "active", source = "isActive")
    Artist createArtistRequestToDomain(CreateArtistRequest createArtistRequest);

    @Mapping(target = "isActive", source = "active")
    CreateArtistResponse domainToCreateArtistResponse(Artist artist);

    @Mapping(target = "active", source = "isActive")
    Artist updateArtistRequestToDomain(UpdateArtistRequest updateArtistRequest);

    @Mapping(target = "isActive", source = "active")
    UpdateArtistResponse domainToUpdateArtistResponse(Artist artist);

    GetArtistResponse domainToGetArtistResponse(Artist restaurant);
}
