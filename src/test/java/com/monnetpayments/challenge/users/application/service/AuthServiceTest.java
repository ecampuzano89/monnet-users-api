package com.monnetpayments.challenge.users.application.service;

import com.monnetpayments.challenge.users.application.dto.LoginRequest;
import com.monnetpayments.challenge.users.infrastructure.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @Test
    void shouldAuthenticateAndGenerateToken() {
        LoginRequest request = new LoginRequest("admin", "admin123");
        UserDetails userDetails = new User("admin", "password", Collections.emptyList());

        when(userDetailsService.loadUserByUsername("admin")).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn("jwt-token");

        String token = authService.authenticate(request);

        assertNotNull(token);
        assertEquals("jwt-token", token);
        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken("admin", "admin123")
        );
    }
}
