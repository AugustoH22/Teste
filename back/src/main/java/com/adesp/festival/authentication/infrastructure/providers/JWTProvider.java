package com.adesp.festival.authentication.infrastructure.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTProvider {

    @Value("${com.adesp.festival.token-secret}")
    private String accessTokenSecret;

    @Value("${com.adesp.festival.refresh-token-secret}")
    private String refreshTokenSecret;

    @Value("${com.adesp.festival.recovery-token-secret}")
    private String recoveryTokenSecret;

    public void verifyTokenExpiration(String token, Algorithm algorithm){
        try{
            Date tokenExpiration = JWT.require(algorithm).build().verify(token).getExpiresAt();
        }
        catch (TokenExpiredException expiredException){
            throw new TokenExpiredException("O Token está expirado. Por favor, faça um novo login!");
        }
    }

    public void verifyRecoveryTokenExpiration(String recoveryToken){
        try{
            Date tokenExpiration = JWT.require(Algorithm.HMAC256(this.recoveryTokenSecret)).build().verify(recoveryToken).getExpiresAt();
        }
        catch (TokenExpiredException expiredException){
            throw new TokenExpiredException("O Token está expirado. Por favor, faça um novo login!");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String validateAccessToken(String token){
        token = token.replace("Bearer ", "");

        try {
            Algorithm algorithm = Algorithm.HMAC256(this.accessTokenSecret);
            return JWT.require(algorithm).build().verify(token).getSubject();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String validateRefreshToken(String token){
        token = token.replace("Bearer ", "");

        try {
            Algorithm algorithm = Algorithm.HMAC256(this.refreshTokenSecret);
            this.verifyTokenExpiration(token, algorithm);
            return JWT.require(algorithm).build().verify(token).getSubject();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String validateRecoveryToken(String token){
        token = token.replace("Bearer ", "");

        try {
            Algorithm algorithm = Algorithm.HMAC256(this.recoveryTokenSecret);
            this.verifyTokenExpiration(token, algorithm);
            return JWT.require(algorithm).build().verify(token).getSubject();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
