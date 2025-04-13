package com.adesp.festival.authentication.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.authentication.application.services.SignInService;
import com.adesp.festival.authentication.application.services.SessionService;
import com.adesp.festival.authentication.application.services.TokenService;
import com.adesp.festival.authentication.domain.entities.User;

import java.util.HashMap;

@UseCase
public class SignInUseCase {

    private final SessionService sessionService;
    private final SignInService signInService;
    private final TokenService tokenService;

    public SignInUseCase(SessionService sessionService, SignInService signInService, TokenService tokenService) {
        this.sessionService = sessionService;
        this.signInService = signInService;
        this.tokenService = tokenService;
    }

    public HashMap<String, String> execute(String login, String password){
        User stored = this.signInService.authenticate(login, password);
        HashMap<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", tokenService.generateAccessToken(stored));
        tokens.put("refreshToken", tokenService.generateRefreshToken(stored));
        this.sessionService.create(stored, tokens.get("refreshToken"));
        return tokens;
    }
}
