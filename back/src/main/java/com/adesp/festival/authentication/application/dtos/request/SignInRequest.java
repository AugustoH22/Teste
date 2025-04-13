package com.adesp.festival.authentication.application.dtos.request;

public record SignInRequest(
        String login,
        String password
) {
}
