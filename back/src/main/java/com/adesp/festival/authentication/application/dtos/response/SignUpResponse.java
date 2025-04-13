package com.adesp.festival.authentication.application.dtos.response;

public record SignUpResponse(
        Long id,
        String name,
        String cpf,
        String username,
        String email,
        String role
) {
}
