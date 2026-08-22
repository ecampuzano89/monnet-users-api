package com.monnetpayments.challenge.users.application.service;

import com.monnetpayments.challenge.users.application.dto.external.JsonPlaceholderUserDto;
import com.monnetpayments.challenge.users.application.mapper.UserMapper;
import com.monnetpayments.challenge.users.domain.model.User;
import com.monnetpayments.challenge.users.domain.repository.UserRepository;
import com.monnetpayments.challenge.users.infrastructure.client.JsonPlaceholderClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSyncService {

    private final JsonPlaceholderClient jsonPlaceholderClient;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public List<User> syncUsers() {
        List<JsonPlaceholderUserDto> externalUsers = jsonPlaceholderClient.fetchUsers();
        List<User> users = userMapper.toEntityList(externalUsers);
        return userRepository.saveAll(users);
    }
}
