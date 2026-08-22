package com.monnetpayments.challenge.users.application.service;

import com.monnetpayments.challenge.users.application.dto.external.JsonPlaceholderUserDto;
import com.monnetpayments.challenge.users.application.mapper.UserMapper;
import com.monnetpayments.challenge.users.domain.model.User;
import com.monnetpayments.challenge.users.domain.repository.UserRepository;
import com.monnetpayments.challenge.users.infrastructure.client.JsonPlaceholderClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserSyncServiceTest {

    @Mock
    private JsonPlaceholderClient jsonPlaceholderClient;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserSyncService userSyncService;

    @Test
    void shouldFetchAndSaveUsers() {
        JsonPlaceholderUserDto external = JsonPlaceholderUserDto.builder().id(1).name("Leanne Graham").build();
        User user = User.builder().externalId(1).name("Leanne Graham").build();

        when(jsonPlaceholderClient.fetchUsers()).thenReturn(Collections.singletonList(external));
        when(userMapper.toEntityList(Collections.singletonList(external))).thenReturn(Collections.singletonList(user));
        when(userRepository.saveAll(Collections.singletonList(user))).thenReturn(Collections.singletonList(user));

        List<User> result = userSyncService.syncUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(jsonPlaceholderClient).fetchUsers();
        verify(userRepository).saveAll(Collections.singletonList(user));
    }
}
