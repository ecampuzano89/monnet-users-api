package com.monnetpayments.challenge.users.infrastructure.client;

import com.monnetpayments.challenge.users.application.dto.external.JsonPlaceholderUserDto;
import com.monnetpayments.challenge.users.infrastructure.config.RestTemplateConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(JsonPlaceholderClient.class)
@Import(RestTemplateConfig.class)
class JsonPlaceholderClientTest {

    @Autowired
    private JsonPlaceholderClient client;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void shouldFetchUsers() {
        String responseBody = "[{\"id\":1,\"name\":\"Leanne Graham\",\"username\":\"Bret\",\"email\":\"Sincere@april.biz\"}]";

        mockServer.expect(requestTo("https://jsonplaceholder.typicode.com/users"))
                .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

        List<JsonPlaceholderUserDto> users = client.fetchUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("Leanne Graham", users.get(0).getName());
    }
}
