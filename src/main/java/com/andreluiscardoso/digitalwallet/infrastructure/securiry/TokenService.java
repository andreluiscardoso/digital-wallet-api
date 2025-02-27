package com.andreluiscardoso.digitalwallet.infrastructure.securiry;

import com.andreluiscardoso.digitalwallet.domain.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    private static final String ISSUER = "digital-wallet-api";
    private static final long EXPIRATION_TIME = TimeUnit.HOURS.toMillis(3);

    @Value("${api.security.jwt-secret-key}")
    private String secretKey;

    public Token generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            var jwt = JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getEmail())
                    .withClaim("userId", user.getId().toString())
                    .withClaim("username", user.getUsername())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusMillis(EXPIRATION_TIME))
                    .sign(algorithm);
            return new Token(jwt);
        } catch (JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }

    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException(e);
        }
    }

}
