package com.monnetpayments.challenge.users.infrastructure.controller;

import com.monnetpayments.challenge.users.application.dto.external.AddressExternalDto;
import com.monnetpayments.challenge.users.application.dto.external.CompanyExternalDto;
import com.monnetpayments.challenge.users.application.dto.external.GeoExternalDto;
import com.monnetpayments.challenge.users.application.dto.external.JsonPlaceholderUserDto;
import com.monnetpayments.challenge.users.application.service.UserSyncService;
import com.monnetpayments.challenge.users.infrastructure.client.JsonPlaceholderClient;
import com.monnetpayments.challenge.users.infrastructure.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;

    @MockBean
    private JsonPlaceholderClient jsonPlaceholderClient;

    @Autowired
    private UserSyncService userSyncService;

    private String token;

    @BeforeEach
    void setUp() {
        UserDetails userDetails = new User("admin", "password", Collections.emptyList());
        token = jwtService.generateToken(userDetails);
        when(jsonPlaceholderClient.fetchUsers()).thenReturn(Collections.singletonList(buildExternalUser()));
    }

    @Test
    void shouldReturnUnauthorizedWithoutToken() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldReturnUsersWithValidToken() throws Exception {
        userSyncService.syncUsers();

        mockMvc.perform(get("/users")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    void shouldSyncUsersWithValidToken() throws Exception {
        mockMvc.perform(post("/users/sync")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private JsonPlaceholderUserDto buildExternalUser() {
        return JsonPlaceholderUserDto.builder()
                .id(1)
                .name("Leanne Graham")
                .username("Bret")
                .email("Sincere@april.biz")
                .phone("1-770-736-8031 x56442")
                .website("hildegard.org")
                .address(AddressExternalDto.builder()
                        .street("Kulas Light")
                        .suite("Apt. 556")
                        .city("Gwenborough")
                        .zipcode("92998-3874")
                        .geo(GeoExternalDto.builder().lat("-37.3159").lng("81.1496").build())
                        .build())
                .company(CompanyExternalDto.builder()
                        .name("Romaguera-Crona")
                        .catchPhrase("Multi-layered client-server neural-net")
                        .bs("harness real-time e-markets")
                        .build())
                .build();
    }
}
