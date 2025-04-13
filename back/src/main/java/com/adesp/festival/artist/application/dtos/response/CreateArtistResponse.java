package com.adesp.festival.artist.application.dtos.response;

import java.sql.Timestamp;

public record CreateArtistResponse(
        Long id,
        String name,
        String cpf,
        String rg,
        String city,
        String uf,
        String telephone,
        String email,
        Boolean isActive,
        Long createdBy,
        Timestamp createdAt
) {
}
