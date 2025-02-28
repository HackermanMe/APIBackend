package com.api.backend.service.impl;

import com.api.backend.dto.request.LoginRequest;
import com.api.backend.dto.request.RegisterRequest;
import com.api.backend.dto.response.AuthResponse;
import com.api.backend.dto.response.UserResponse;
import com.api.backend.entity.Token;
import com.api.backend.entity.User;
import com.api.backend.mapper.AuthMapper;
import com.api.backend.repository.TokenRepository;
import com.api.backend.repository.UserRepository;
import com.api.backend.service.AuthService;
import com.api.backend.utils.JwtUtil;
import com.api.backend.utils.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthMapper authMapper;

    public AuthServiceImpl(UserRepository userRepository, 
                         TokenRepository tokenRepository,
                         PasswordEncoder passwordEncoder,
                         JwtUtil jwtUtil,
                         AuthMapper authMapper) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authMapper = authMapper;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new ApiException("Email ou mot de passe incorrect", HttpStatus.UNAUTHORIZED));

        if (!passwordEncoder.matches(request.getPassword(), user.getMotDePasse())) {
            throw new ApiException("Email ou mot de passe incorrect", HttpStatus.UNAUTHORIZED);
        }

        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        saveUserToken(user, accessToken);

        return authMapper.toAuthResponse(accessToken, refreshToken, user);
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ApiException("Cet email est déjà utilisé", HttpStatus.CONFLICT);
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new ApiException("Les mots de passe ne correspondent pas", HttpStatus.BAD_REQUEST);
        }

        User user = authMapper.toEntity(request);
        user = userRepository.save(user);

        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        saveUserToken(user, accessToken);

        return authMapper.toAuthResponse(accessToken, refreshToken, user);
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = new Token();
        token.setUser(user);
        token.setToken(jwtToken);
        token.setTokenType("BEARER");
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Override
    public AuthResponse refreshToken(String refreshToken) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refreshToken'");
    }

    @Override
    public void logout(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            var storedToken = tokenRepository.findByToken(token)
                    .orElseThrow(() -> new ApiException("Token invalide", HttpStatus.BAD_REQUEST));
                    
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
        }
    }
}