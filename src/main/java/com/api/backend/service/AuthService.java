package com.api.backend.service;

import com.api.backend.dto.request.LoginRequest;
import com.api.backend.dto.request.RegisterRequest;
import com.api.backend.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
    AuthResponse refreshToken(String refreshToken);
    void logout(String token);
} 