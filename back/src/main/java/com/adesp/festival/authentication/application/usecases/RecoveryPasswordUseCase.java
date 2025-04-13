package com.adesp.festival.authentication.application.usecases;

import com.adesp.festival.UseCase;
import com.adesp.festival.authentication.domain.entities.User;
import com.adesp.festival.authentication.domain.exceptions.NotFoundUserException;
import com.adesp.festival.authentication.domain.repositories.UserRepository;
import com.adesp.festival.authentication.infrastructure.providers.JWTProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;

import java.io.UnsupportedEncodingException;

@UseCase
public class RecoveryPasswordUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;
    @Value("${com.adesp.festival.recovery-token-secret}")
    private String recoveryTokenSecret;

    public RecoveryPasswordUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    private String getRecoveryTokenClaimContent(String token, String claim){
        return JWT.decode(token).getClaim(claim).asString();
    }

    public void execute(String password, String token) {
        this.jwtProvider.validateRecoveryToken(token);
        this.jwtProvider.verifyRecoveryTokenExpiration(token);
        Long userId = Long.parseLong(this.getRecoveryTokenClaimContent(token, "subject"));
        User storedUser = this. userRepository.findById(userId)
                .map(user -> {
                    user.setPassword(this.passwordEncoder.encode(password));
                    return user;
                })
                .orElseThrow(() -> new NotFoundUserException());
    }
}
