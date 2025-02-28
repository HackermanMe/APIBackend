package com.api.backend.mapper;

import com.api.backend.dto.request.ReviewRequest;
import com.api.backend.dto.response.ReviewResponse;
import com.api.backend.dto.response.UserResponse;
import com.api.backend.entity.Review;
import com.api.backend.repository.UserRepository;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class ReviewMapper {
    private final UserRepository userRepository;

    public ReviewMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Review toEntity(ReviewRequest request) {
        if (request == null) {
            return null;
        }
        
        Review entity = new Review();
        entity.setTrackingId(UUID.randomUUID());
        if (request.getUtilisateurId() != null) {
            entity.setUser(userRepository.findByTrackingId(request.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("User non trouvé avec trackingId: " + request.getUtilisateurId())));
        }
        entity.setNote(request.getNote());
        entity.setCommentaire(request.getCommentaire());
        entity.setId(request.getIditem());
        // entity.setItemType(request.getTypeitem());
        return entity;
    }
    
    public ReviewResponse toResponse(Review entity) {
        if (entity == null) {
            return null;
        }
        
        ReviewResponse response = new ReviewResponse();
        response.setTrackingid(entity.getTrackingId());
        if (entity.getUser() != null) {
            UserResponse r = UserResponse.fromUser(entity.getUser());
            response.setUtilisateur(r);
        }
        response.setNote(entity.getNote());
        response.setCommentaire(entity.getCommentaire());
        response.setIditem(entity.getId());
        // response.setTypeitem(entity.getItemType());
        return response;
    }
}