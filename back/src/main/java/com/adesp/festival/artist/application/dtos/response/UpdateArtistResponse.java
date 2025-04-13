package com.adesp.festival.artist.application.dtos.response;

import java.sql.Timestamp;

public record UpdateArtistResponse(
        Long id,
        String name,
        String cpf,
        String rg,
        String city,
        String uf,
        String telephone,
        String email,
        Long createdBy,
        Boolean isActive,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
