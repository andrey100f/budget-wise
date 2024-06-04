package com.ubb.bugetwise_auth.client;

import com.ubb.bugetwise_auth.model.AuthRequest;
import com.ubb.bugetwise_auth.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "user-service", url = "${application.config.users-url}")
public interface UsersClient {

    @PostMapping("/login")
    User loginUser(@RequestBody AuthRequest request);

    @PostMapping
    User registerUser(@RequestBody AuthRequest request);

}