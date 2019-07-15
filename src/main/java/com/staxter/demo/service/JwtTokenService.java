package com.staxter.demo.service;

import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;
import static java.time.LocalDateTime.now;
import static java.time.ZoneId.systemDefault;
import static java.util.Date.from;

@Service
public class JwtTokenService {

    private static final String ISSUER = "demo-application";
    private static final String USER_ID = "USER_ID";

    @Value("${jwt.security.token.secret}")
    private String secret;

    @Value("${jwt.security.token.expiration.minutes}")
    private Integer expirationMinutes;

    /**
     * Assumed generated token stored and validated in security filter
     */
    public String generateToken(Integer id) {
        Date expiresAt = from(now()
                .plusMinutes(expirationMinutes)
                .atZone(systemDefault())
                .toInstant());
        return JWT.create()
                .withClaim(USER_ID, id)
                .withExpiresAt(expiresAt)
                .withIssuer(ISSUER)
                .sign(HMAC256(secret));
    }
}
