package com.adesp.festival.artist.application.dtos.response;

public record GetArtistResponse(
        Long id,
        String name,
        String cpf,
        String rg,
        String city,
        String uf,
        String telephone,
        String email,
        Long createdBy
) {
}
