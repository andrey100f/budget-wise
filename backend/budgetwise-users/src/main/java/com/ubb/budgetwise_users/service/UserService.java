package com.ubb.budgetwise_users.service;

import com.ubb.budgetwise_users.model.dto.AddUserDto;
import com.ubb.budgetwise_users.model.dto.LoginDto;
import com.ubb.budgetwise_users.model.dto.UserDto;
import com.ubb.budgetwise_users.model.mapper.UserMapper;
import com.ubb.budgetwise_users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public UserDto getUserById(String id) {
        return this.userRepository.findById(id)
            .map(this.userMapper::mapDtoDto)
            .orElseThrow();
    }

    public UserDto addUser(AddUserDto userDto) {
        return Optional.of(userDto)
            .map(this.userMapper::mapFromAddDtoToModel)
            .map(this.userRepository::save)
            .map(this.userMapper::mapDtoDto)
            .orElseThrow();
    }

    public UserDto updateUser(UserDto user) {
        return this.userRepository.findById(user.id())
            .map(usr -> this.userRepository.save(this.userMapper.mapToModel(user)))
            .map(this.userMapper::mapDtoDto)
            .orElseThrow();
    }

    public UserDto loginUser(LoginDto loginDto) {
        return this.userRepository.findByUsernameAndPassword(loginDto.username(), loginDto.password())
            .map(this.userMapper::mapDtoDto)
            .orElseThrow();
    }

    public void deleteUser(String id) {
        this.userRepository.deleteById(id);
    }

}
