package com.adesp.festival.authentication.application.dtos.request;

public record InviteUserRequest(
        String email,
        String role
) {
}
