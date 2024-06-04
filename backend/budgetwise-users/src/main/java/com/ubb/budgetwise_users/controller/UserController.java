package com.ubb.budgetwise_users.controller;

import com.ubb.budgetwise_users.model.dto.AddUserDto;
import com.ubb.budgetwise_users.model.dto.LoginDto;
import com.ubb.budgetwise_users.model.dto.UserDto;
import com.ubb.budgetwise_users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> loginUser(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(this.userService.loginUser(loginDto));
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody AddUserDto userDto) throws URISyntaxException {
        UserDto newUser = this.userService.addUser(userDto);
        return ResponseEntity.created(new URI("/api/users/" + newUser.id()))
            .body(newUser);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user) {
        return ResponseEntity.ok(this.userService.updateUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
