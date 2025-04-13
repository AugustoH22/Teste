package com.adesp.festival.music.application.dtos.response;

import java.sql.Timestamp;

public record FindMusicResponse(
        String title,
        Long composerId,
        Long interpreterId,
        Boolean isActive,
        Long createdBy,
        Timestamp createdAt
) {
}
