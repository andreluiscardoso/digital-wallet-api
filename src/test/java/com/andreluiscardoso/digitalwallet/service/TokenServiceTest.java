package com.andreluiscardoso.digitalwallet.service;

import com.andreluiscardoso.digitalwallet.domain.model.User;
import com.andreluiscardoso.digitalwallet.infrastructure.securiry.Token;
import com.andreluiscardoso.digitalwallet.infrastructure.securiry.TokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = TokenService.class)
public class TokenServiceTest {

    @Autowired
    private TokenService tokenService;
    private User user;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("api.security.jwt-secret-key", () -> "mySecretKey");
    }

    @BeforeEach
    void setup() {
        user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("username");
        user.setEmail("user@example.com");
    }

    @Test
    void testGenerateToken_ShouldReturnValidToken() {
        Token token = tokenService.generateToken(user);

        assertNotNull(token, "Token cannot be null");
        assertFalse(token.value().isEmpty(), "Token cannot be empty");
    }

    @Test
    void testGenerateToken_ShouldContainUserEmail() {
        Token token = tokenService.generateToken(user);
        DecodedJWT decodedJWT = JWT.decode(token.value());

        String email = decodedJWT.getSubject();
        assertNotNull(email, "Email must not be null");
        assertEquals(user.getEmail(), email, "Email in token does not match user email");
    }

    @Test
    void testGenerateToken_ShouldContainUserId() {
        Token token = tokenService.generateToken(user);
        DecodedJWT decodedJWT = JWT.decode(token.value());

        assertEquals(decodedJWT.getClaim("userId").asString(), user.getId().toString(), "User ID does not match");
    }

    @Test
    void testGenerateToken_ShouldContainUsername() {
        Token token = tokenService.generateToken(user);
        DecodedJWT decodedJWT = JWT.decode(token.value());

        assertEquals(user.getUsername(), decodedJWT.getClaim("username").asString(), "Username does not match");
    }

    @Test
    void testTokenExpiration_ShouldExpireAfterTime() {
        Token token = tokenService.generateToken(user);

        Instant expirationTime = JWT.decode(token.value()).getExpiresAt().toInstant();
        Instant now = Instant.now();

        assertTrue(expirationTime.isAfter(now), "Token expiration must be in the future");
        assertTrue(expirationTime.isBefore(now.plusMillis(TimeUnit.HOURS.toMillis(3))), "Token expiration must occur within 3 hours");
    }

    @Test
    void testGetSubject_ShouldReturnCorrectSubject() {
        Token token = tokenService.generateToken(user);
        String subject = tokenService.getSubject(token.value());

        assertEquals(user.getEmail(), subject, "The subject of the token must be the user's email");
    }

    @Test
    void testGetSubject_ShouldThrowExceptionForInvalidToken() {
        String invalidToken = "invalid.token.here";

        assertThrows(RuntimeException.class, () -> {
            tokenService.getSubject(invalidToken);
        }, "Should throw exception when trying to decode an invalid token");
    }

    @Test
    void testGetSubject_ShouldThrowExceptionForExpiredToken() {
        Algorithm algorithm = Algorithm.HMAC256("mySecretKey");
        String expiredToken = JWT.create()
                .withIssuer("digital-wallet-api")
                .withSubject(user.getEmail())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().minusSeconds(3600))
                .sign(algorithm);

        assertThrows(RuntimeException.class, () -> {
            tokenService.getSubject(expiredToken);
        }, "Should throw exception for expired token");
    }

}
