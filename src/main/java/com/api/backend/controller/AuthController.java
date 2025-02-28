package com.api.backend.controller;

import com.api.backend.dto.request.LoginRequest;
import com.api.backend.dto.request.RegisterRequest;
import com.api.backend.dto.response.AuthResponse;
import com.api.backend.service.AuthService;
import com.api.backend.utils.GlobalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<AuthResponse>> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Connexion réussie", response));
    }

    @PostMapping("/register")
    public ResponseEntity<GlobalResponse<AuthResponse>> register(@RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Inscription réussie", response));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<GlobalResponse<AuthResponse>> refreshToken(@RequestHeader("Authorization") String refreshToken) {
        AuthResponse response = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Token rafraîchi avec succès", response));
    }

    @PostMapping("/logout")
    public ResponseEntity<GlobalResponse<Void>> logout(@RequestHeader("Authorization") String token) {
        authService.logout(token);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Déconnexion réussie", null));
    }
} 