package com.api.backend.service.impl;

import com.api.backend.dto.request.ReviewRequest;
import com.api.backend.dto.response.ReviewResponse;
import com.api.backend.entity.Review;
import com.api.backend.mapper.ReviewMapper;
import com.api.backend.repository.ReviewRepository;
import com.api.backend.service.ReviewService;
import com.api.backend.utils.exception.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final ReviewMapper mapper;

    public ReviewServiceImpl(ReviewRepository repository, ReviewMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ReviewResponse create(ReviewRequest request) {
        try {
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getNote() < 0 || request.getNote() > 5) {
                throw new ApiException("La note doit être comprise entre 0 et 5", HttpStatus.BAD_REQUEST);
            }
            if (request.getCommentaire() == null || request.getCommentaire().trim().isEmpty()) {
                throw new ApiException("Le commentaire est obligatoire", HttpStatus.BAD_REQUEST);
            }

            Review entity = mapper.toEntity(request);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la création de l'avis: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ReviewResponse update(UUID trackingId, ReviewRequest request) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getNote() < 0 || request.getNote() > 5) {
                throw new ApiException("La note doit être comprise entre 0 et 5", HttpStatus.BAD_REQUEST);
            }
            if (request.getCommentaire() == null || request.getCommentaire().trim().isEmpty()) {
                throw new ApiException("Le commentaire est obligatoire", HttpStatus.BAD_REQUEST);
            }

            Review existingReview = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Avis non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            Review entity = mapper.toEntity(request);
            entity.setId(existingReview.getId());
            entity.setTrackingId(trackingId);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la mise à jour de l'avis: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ReviewResponse findById(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Review entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Avis non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la recherche de l'avis: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<ReviewResponse> findAll() {
        try {
            List<Review> entities = repository.findAllOrdered();
            return entities.stream()
                .map(mapper::toResponse)
                .toList();
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération des avis: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Review entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Avis non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            repository.delete(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la suppression de l'avis: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}