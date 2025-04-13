package com.adesp.festival.artist.application.dtos.request;

public record UpdateArtistRequest(
        String name,
        String cpf,
        String rg,
        String city,
        String uf,
        String telephone,
        String email,
        Boolean isActive
) {
}
