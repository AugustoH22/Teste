package com.adesp.festival.authentication.application.services;

import com.adesp.festival.authentication.domain.entities.Session;
import com.adesp.festival.authentication.domain.entities.User;
import com.adesp.festival.authentication.domain.exceptions.NotFoundSessionException;
import com.adesp.festival.authentication.domain.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Duration;
import java.time.Instant;

@Service
public class SessionService {

    @Value("${com.adesp.festival.refresh-token-days}")
    private Integer refreshTokenDuration;

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session create(User user, String refreshToken){
        return this.sessionRepository.save(new Session(
                refreshToken,
                user,
                Instant.now().plus(Duration.ofDays(7)),
                false,
                false
        ));
    }

    public Session findSessionByRefreshToken(String refreshToken){
        return this.sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new NotFoundSessionException());
    }
}
