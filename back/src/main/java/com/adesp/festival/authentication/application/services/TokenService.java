package com.adesp.festival.authentication.application.services;

import com.adesp.festival.authentication.domain.entities.Session;
import com.adesp.festival.authentication.domain.entities.User;
import com.adesp.festival.authentication.domain.enums.Roles;
import com.adesp.festival.authentication.domain.exceptions.ExpiredSessionException;
import com.adesp.festival.authentication.domain.repositories.SessionRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class TokenService {

    @Value("${com.adesp.festival.token-secret}")
    private String accessTokenSecret;

    @Value("${com.adesp.festival.refresh-token-secret}")
    private String refreshTokenSecret;

    @Value("${com.adesp.festival.recovery-token-secret}")
    private String recoveryTokenSecret;

    @Value("${com.adesp.festival.access-token-hours}")
    private Integer accessTokenDuration;

    @Value("${com.adesp.festival.refresh-token-days}")
    private Integer refreshTokenDuration;

    @Value("${com.adesp.festival.recovery-token-minutes}")
    private Integer recoveryTokenDuration;

    @Value("${com.adesp.festival.invite-token-hours}")
    private Integer inviteTokenDuration;

    @Value("${com.adesp.festival.invite-token-secret}")
    private String inviteTokenSecret;

    private final SessionRepository sessionRepository;

    public TokenService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public String generateAccessToken(User storedUser){
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.accessTokenSecret);
            return JWT.create().withIssuer("ADESP Festival Developed By IFTM.")
                    .withSubject(storedUser.getId().toString())
                    .withExpiresAt(Date.from(Instant.now().plus(Duration.ofHours(this.accessTokenDuration))))
                    .withIssuedAt(Date.from(Instant.now()))
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateRefreshToken(User storedUser){
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.refreshTokenSecret);
            return JWT.create().withIssuer("ADESP Festival Developed By IFTM.")
                    .withSubject(storedUser.getId().toString())
                    .withExpiresAt(Date.from(Instant.now().plus(Duration.ofDays(this.refreshTokenDuration))))
                    .withIssuedAt(Date.from(Instant.now()))
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateRecoveryToken(String subject){
        try{
            Algorithm algorithm = Algorithm.HMAC256(this.recoveryTokenSecret);
            return JWT.create().withIssuer("ADESP Festival Developed By IFTM.")
                    .withSubject(subject)
                    .withExpiresAt(Date.from(Instant.now().plus(Duration.ofDays(this.recoveryTokenDuration))))
                    .withIssuedAt(Date.from(Instant.now()))
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateInviteToken(String subject, String destinationEmail, Roles role){
        try{
            Algorithm algorithm = Algorithm.HMAC256(this.inviteTokenSecret);
            return JWT.create().withIssuer("ADESP Festival Developed By IFTM.")
                    .withSubject(subject)
                    .withClaim("email", destinationEmail)
                    .withClaim("role", role.name())
                    .withExpiresAt(Date.from(Instant.now().plus(Duration.ofHours(this.inviteTokenDuration))))
                    .withIssuedAt(Date.from(Instant.now()))
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean isSessionExpired(Session session){
        if(session.getExpirationDate().compareTo(Instant.now()) < 0){
            this.sessionRepository.deleteSessionById(session.getId());
            throw new ExpiredSessionException();
        }

        return false;
    }

}
