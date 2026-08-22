package com.monnetpayments.challenge.users.application.service;

import com.monnetpayments.challenge.users.application.dto.UserDto;
import com.monnetpayments.challenge.users.application.mapper.UserMapper;
import com.monnetpayments.challenge.users.domain.model.User;
import com.monnetpayments.challenge.users.domain.repository.UserRepository;
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
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnAllUsersAsDto() {
        User user = User.builder().externalId(1).name("Leanne Graham").build();
        UserDto dto = UserDto.builder().id(1).name("Leanne Graham").build();

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        when(userMapper.toDtoList(Collections.singletonList(user))).thenReturn(Collections.singletonList(dto));

        List<UserDto> result = userService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Leanne Graham", result.get(0).getName());
        verify(userRepository).findAll();
    }
}
