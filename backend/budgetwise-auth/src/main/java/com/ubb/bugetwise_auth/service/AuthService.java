package com.ubb.bugetwise_auth.service;

import com.ubb.bugetwise_auth.client.UsersClient;
import com.ubb.bugetwise_auth.model.AuthRequest;
import com.ubb.bugetwise_auth.model.AuthResponse;
import com.ubb.bugetwise_auth.model.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersClient usersClient;
    private final JwtUtil jwtUtil;

    public User register(AuthRequest request) {
        return this.usersClient.registerUser(request);
    }

    public AuthResponse login(AuthRequest request) {
        User user = usersClient.loginUser(request);

        String accessToken = jwtUtil.generate(user.getId(), user.getUsername(), "USER", "ACCESS");
        String refreshToken = jwtUtil.generate(user.getId(), user.getUsername(), "USER", "REFRESH");

        return AuthResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }

}
