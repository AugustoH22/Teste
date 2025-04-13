package com.adesp.festival.authentication.application.dtos.response;

public record SignInResponse(
        String accessToken,
        String refreshToken
) {
}
