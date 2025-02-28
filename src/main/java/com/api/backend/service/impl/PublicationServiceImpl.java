package com.api.backend.service.impl;

import com.api.backend.dto.request.PublicationRequest;
import com.api.backend.dto.response.PublicationResponse;
import com.api.backend.entity.Publication;
import com.api.backend.mapper.PublicationMapper;
import com.api.backend.repository.PublicationRepository;
import com.api.backend.service.PublicationService;
import com.api.backend.utils.exception.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PublicationServiceImpl implements PublicationService {

    private final PublicationRepository repository;
    private final PublicationMapper mapper;

    public PublicationServiceImpl(PublicationRepository repository, PublicationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PublicationResponse create(PublicationRequest request) {
        try {
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getTitre() == null || request.getTitre().trim().isEmpty()) {
                throw new ApiException("Le titre est obligatoire", HttpStatus.BAD_REQUEST);
            }
            if (request.getContenu() == null || request.getContenu().trim().isEmpty()) {
                throw new ApiException("Le contenu est obligatoire", HttpStatus.BAD_REQUEST);
            }

            Publication entity = mapper.toEntity(request);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la création de la publication: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public PublicationResponse update(UUID trackingId, PublicationRequest request) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getTitre() == null || request.getTitre().trim().isEmpty()) {
                throw new ApiException("Le titre est obligatoire", HttpStatus.BAD_REQUEST);
            }
            if (request.getContenu() == null || request.getContenu().trim().isEmpty()) {
                throw new ApiException("Le contenu est obligatoire", HttpStatus.BAD_REQUEST);
            }

            Publication existingPublication = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Publication non trouvée avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            Publication entity = mapper.toEntity(request);
            entity.setId(existingPublication.getId());
            entity.setTrackingId(trackingId);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la mise à jour de la publication: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public PublicationResponse findById(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Publication entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Publication non trouvée avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la recherche de la publication: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<PublicationResponse> findAll() {
        try {
            List<Publication> entities = repository.findAllOrdered();
            return entities.stream()
                .map(mapper::toResponse)
                .toList();
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération des publications: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Publication entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Publication non trouvée avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            repository.delete(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la suppression de la publication: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}