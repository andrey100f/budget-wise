package com.ubb.budgetwise_users.service;

import com.ubb.budgetwise_users.model.dto.UserDto;
import com.ubb.budgetwise_users.model.mapper.UserMapper;
import com.ubb.budgetwise_users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAllUsers() {
        return this.userRepository.findAll().stream()
            .map(this.userMapper::mapDtoDto)
            .toList();
    }

}
