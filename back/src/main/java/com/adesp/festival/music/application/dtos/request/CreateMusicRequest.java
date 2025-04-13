package com.adesp.festival.music.application.dtos.request;

public record CreateMusicRequest(
        String title,
        Long composerId,
        Long interpreterId,
        Boolean isActive
) {
}
