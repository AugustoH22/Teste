package com.adesp.festival.music.application.dtos.request;

public record UpdateMusicRequest(
        String title,
        Long composerId,
        Long interpreterId,
        Boolean isActive
) {
}
