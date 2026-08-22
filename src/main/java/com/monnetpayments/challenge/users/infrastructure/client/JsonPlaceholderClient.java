package com.monnetpayments.challenge.users.infrastructure.client;

import com.monnetpayments.challenge.users.application.dto.external.JsonPlaceholderUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonPlaceholderClient {

    private final RestTemplate restTemplate;

    @Value("${jsonplaceholder.url}")
    private String jsonPlaceholderUrl;

    public List<JsonPlaceholderUserDto> fetchUsers() {
        ResponseEntity<List<JsonPlaceholderUserDto>> response = restTemplate.exchange(
                jsonPlaceholderUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody() != null ? response.getBody() : Collections.emptyList();
    }
}
