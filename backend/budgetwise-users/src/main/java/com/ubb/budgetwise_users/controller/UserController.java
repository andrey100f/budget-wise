package com.ubb.budgetwise_users.controller;

import com.ubb.budgetwise_users.model.dto.AddUserDto;
import com.ubb.budgetwise_users.model.dto.UserDto;
import com.ubb.budgetwise_users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody AddUserDto userDto) throws URISyntaxException {
        UserDto newUser = this.userService.addUser(userDto);
        return ResponseEntity.created(new URI("/api/users/" + newUser.id()))
            .body(newUser);
    }

}
