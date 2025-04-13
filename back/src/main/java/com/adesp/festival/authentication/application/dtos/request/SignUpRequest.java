package com.adesp.festival.authentication.application.dtos.request;

public record SignUpRequest(
        String inviteToken,
        String name,
        String cpf,
        String username,
        String password
) {
}
