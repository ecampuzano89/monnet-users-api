package com.monnetpayments.challenge.users.infrastructure.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JwtServiceTest {

    private final JwtService jwtService = new JwtService();

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(jwtService, "secret", "monnet-challenge-secret-key-2026");
        ReflectionTestUtils.setField(jwtService, "expiration", 86400000L);
    }

    @Test
    void shouldGenerateTokenAndExtractUsername() {
        UserDetails userDetails = User.builder().username("admin").password("password").roles("ADMIN").build();

        String token = jwtService.generateToken(userDetails);

        assertNotNull(token);
        assertEquals("admin", jwtService.extractUsername(token));
    }

    @Test
    void shouldValidateTokenForCorrectUser() {
        UserDetails userDetails = User.builder().username("admin").password("password").roles("ADMIN").build();
        String token = jwtService.generateToken(userDetails);

        assertTrue(jwtService.validateToken(token, userDetails));
    }

    @Test
    void shouldNotValidateTokenForDifferentUser() {
        UserDetails userDetails = User.builder().username("admin").password("password").roles("ADMIN").build();
        UserDetails otherUser = User.builder().username("other").password("password").roles("ADMIN").build();
        String token = jwtService.generateToken(userDetails);

        assertFalse(jwtService.validateToken(token, otherUser));
    }
}
