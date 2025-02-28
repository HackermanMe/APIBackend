package com.api.backend.mapper;

import com.api.backend.dto.request.LandRequest;
import com.api.backend.dto.response.LandResponse;
import com.api.backend.dto.response.ReviewResponse;
import com.api.backend.dto.response.UserResponse;
import com.api.backend.entity.Land;
import com.api.backend.entity.Review;
import com.api.backend.repository.ReviewRepository;
import com.api.backend.repository.UserRepository;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LandMapper {
    private final UserRepository proprietaireRepository;
    private final ReviewRepository reviewsRepository;

    public LandMapper(UserRepository proprietaireRepository, ReviewRepository reviewsRepository) {
        this.proprietaireRepository = proprietaireRepository;
        this.reviewsRepository = reviewsRepository;
    }

    public Land toEntity(LandRequest request) {
        if (request == null) {
            return null;
        }
        
        Land entity = new Land();
        entity.setTrackingId(UUID.randomUUID());
        entity.setTitre(request.getTitre());
        entity.setSurface(request.getSurface());
        entity.setAdresse(request.getAdresse());
        entity.setVille(request.getVille());
        entity.setDistrict(request.getDistrict());
        entity.setDescription(request.getDescription());
        entity.setPrix(request.getPrix());
        entity.setNegociable(request.isNegociable());
        entity.setPaiementEnPlus(request.isPaiementenplus());
        entity.setAEau(request.isAEau());
        entity.setAElectricite(request.isAElectricite());
        entity.setConstructible(request.isConstructible());
        entity.setCaracteristiques(request.getCaracteristiques());
        entity.setImages(request.getImages());
        if (request.getProprietaireId() != null) {
            entity.setProprietaire(proprietaireRepository.findByTrackingId(request.getProprietaireId())
                .orElseThrow(() -> new RuntimeException("User non trouvé avec trackingId: " + request.getProprietaireId())));
        }
        entity.setDocumentsCadastraux(request.getDocumentsCadastraux());
        entity.setCertificatUrbanisme(request.getCertificatUrbanisme());
        
        entity.setNoteMoyenne(request.getNotemoyenne());
        entity.setNombreAvis(request.getNombreavis());
        return entity;
    }
    
    public LandResponse toResponse(Land entity) {
        if (entity == null) {
            return null;
        }
        
        LandResponse response = new LandResponse();
        response.setTrackingId(entity.getTrackingId());
        response.setTitre(entity.getTitre());
        response.setSurface(entity.getSurface());
        response.setAdresse(entity.getAdresse());
        response.setVille(entity.getVille());
        response.setDistrict(entity.getDistrict());
        response.setDescription(entity.getDescription());
        response.setPrix(entity.getPrix());
        response.setNegociable(entity.isNegociable());
        response.setPaiementEnPlus(entity.isPaiementEnPlus());
        response.setAEau(entity.isAEau());
        response.setAElectricite(entity.isAElectricite());
        response.setConstructible(entity.isConstructible());
        response.setCaracteristiques(entity.getCaracteristiques());
        response.setImages(entity.getImages());
        if (entity.getProprietaire() != null) {
            UserResponse r = UserResponse.fromUser(entity.getProprietaire());
            response.setProprietaire(r);
        }
        response.setNoteMoyenne(entity.getNoteMoyenne());
        response.setNombreAvis(entity.getNombreAvis());
        return response;
    }

    private ReviewResponse toResponse(Review review) {
        if (review == null) return null;
        ReviewResponse response = new ReviewResponse();
        response.setTrackingid(review.getTrackingId());
        return response;
    }
}