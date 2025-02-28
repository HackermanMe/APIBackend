package com.api.backend.mapper;

import com.api.backend.dto.request.LoginRequest;
import com.api.backend.dto.request.RegisterRequest;
import com.api.backend.dto.response.AuthResponse;
import com.api.backend.dto.response.UserResponse;
import com.api.backend.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class AuthMapper {
    private final PasswordEncoder passwordEncoder;

    public AuthMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toEntity(RegisterRequest request) {
        if (request == null) {
            return null;
        }

        User entity = new User();
        entity.setTrackingId(UUID.randomUUID());
        entity.setPrenom(request.getPrenom());
        entity.setNom(request.getNom());
        entity.setEmail(request.getEmail());
        entity.setMotDePasse(passwordEncoder.encode(request.getPassword()));
        entity.setNumeroDeTelephone(request.getNumeroDeTelephone());
        entity.setRole("USER");
        entity.setActif(true);
        return entity;
    }

    public AuthResponse toAuthResponse(String accessToken, String refreshToken, User user) {
        AuthResponse response = new AuthResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setTokenType("Bearer");
        response.setExpiresIn(86400L); // 24 heures en secondes
        response.setUser(UserResponse.fromUser(user));
        return response;
    }
}