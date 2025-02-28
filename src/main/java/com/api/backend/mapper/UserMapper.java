package com.api.backend.mapper;

import com.api.backend.dto.request.UserRequest;
import com.api.backend.dto.response.UserResponse;
import com.api.backend.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toEntity(UserRequest request) {
        if (request == null) {
            return null;
        }
        
        User entity = new User();
        entity.setTrackingId(UUID.randomUUID());
        entity.setPrenom(request.getPrenom());
        entity.setNom(request.getNom());
        entity.setEmail(request.getEmail());
        entity.setMotDePasse(passwordEncoder.encode(request.getMotdepasse()));
        entity.setNumeroDeTelephone(request.getNumerodetelephone());
        entity.setRole(request.getRole() != null ? request.getRole() : "USER");
        entity.setActif(true);
        return entity;
    }
    
    public UserResponse toResponse(User entity) {
        if (entity == null) {
            return null;
        }
        
        return UserResponse.builder()
            .id(entity.getTrackingId().toString())
            .email(entity.getEmail())
            .role(entity.getRole())
            .prenom(entity.getPrenom())
            .nom(entity.getNom())
            .numeroDeTelephone(entity.getNumeroDeTelephone())
            .imageDeProfil(entity.getImageDeProfil())
            .build();
    }
}