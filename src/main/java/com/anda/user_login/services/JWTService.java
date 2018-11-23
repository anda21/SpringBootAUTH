package com.anda.user_login.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JWTService {
    //Lo standard JWT, formalmente RFC 7519, è un metodo per la generazione
    // di token di accesso a servizi web, basato su JSON.

    /**
     * Il server genera e firma il token tramite una chiave privata,
     * così da poter validare le informazioni
     * ed estrarle nel momento in cui le riceve dal client.
     */

    private Algorithm algorithm;
    private int defaultExpiration;

    public JWTService(@Value("jwt.secret") String secret, @Value("${jwt.defaultExpirationMillis}") int defaultExpirationMillis) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.defaultExpiration = defaultExpirationMillis;
    }

    public String create(String username) {
        Instant issuedAt = Instant.now();
        return JWT.create()
                .withIssuedAt(Date.from(issuedAt))
                .withExpiresAt(Date.from(issuedAt.plusSeconds(defaultExpiration)))
                .withClaim("username", username)
                .sign(algorithm);
    }

    public Map<String, Object> verify(String token) throws TokenVerificationException {
        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        try{
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims().entrySet()
                    .stream()
                    .collect(Collectors.toMap(e -> e.getKey(), e-> e.getValue().as(Object.class)));

        }catch(Exception e) {
            throw new TokenVerificationException(e);

        }
    }

}
