package com.monnetpayments.challenge.users.application.service;

import com.monnetpayments.challenge.users.application.dto.UserDto;
import com.monnetpayments.challenge.users.application.mapper.UserMapper;
import com.monnetpayments.challenge.users.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userMapper.toDtoList(userRepository.findAll());
    }
}
