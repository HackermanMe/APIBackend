package com.api.backend.service.impl;

import com.api.backend.dto.request.UserRequest;
import com.api.backend.dto.response.UserResponse;
import com.api.backend.entity.User;
import com.api.backend.mapper.UserMapper;
import com.api.backend.repository.UserRepository;
import com.api.backend.service.UserService;
import com.api.backend.utils.exception.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserResponse create(UserRequest request) {
        try {
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }
            
            // Vérifier si l'email existe déjà
            if (repository.existsByEmail(request.getEmail())) {
                throw new ApiException("Un utilisateur avec cet email existe déjà", HttpStatus.CONFLICT);
            }

            User entity = mapper.toEntity(request);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la création de l'utilisateur: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserResponse update(UUID trackingId, UserRequest request) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            User existingUser = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Utilisateur non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            // Vérifier si le nouvel email existe déjà pour un autre utilisateur
            if (!request.getEmail().equals(existingUser.getEmail()) && 
                repository.existsByEmail(request.getEmail())) {
                throw new ApiException("Un utilisateur avec cet email existe déjà", HttpStatus.CONFLICT);
            }

            User entity = mapper.toEntity(request);
            entity.setId(existingUser.getId());
            entity.setTrackingId(trackingId);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la mise à jour de l'utilisateur: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserResponse findById(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            User entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Utilisateur non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la recherche de l'utilisateur: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<UserResponse> findAll() {
        try {
            List<User> entities = repository.findAllOrdered();
            return entities.stream()
                .map(mapper::toResponse)
                .toList();
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération des utilisateurs: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            User entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Utilisateur non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            repository.delete(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la suppression de l'utilisateur: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}