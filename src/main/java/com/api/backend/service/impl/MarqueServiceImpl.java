package com.api.backend.service.impl;

import com.api.backend.dto.request.MarqueRequest;
import com.api.backend.dto.response.MarqueResponse;
import com.api.backend.entity.Marque;
import com.api.backend.mapper.MarqueMapper;
import com.api.backend.repository.MarqueRepository;
import com.api.backend.service.MarqueService;
import com.api.backend.utils.exception.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MarqueServiceImpl implements MarqueService {

    private final MarqueRepository repository;
    private final MarqueMapper mapper;

    public MarqueServiceImpl(MarqueRepository repository, MarqueMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MarqueResponse create(MarqueRequest request) {
        try {
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getNom() == null || request.getNom().trim().isEmpty()) {
                throw new ApiException("Le nom de la marque est obligatoire", HttpStatus.BAD_REQUEST);
            }

            Marque entity = mapper.toEntity(request);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la création de la marque: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MarqueResponse update(UUID trackingId, MarqueRequest request) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getNom() == null || request.getNom().trim().isEmpty()) {
                throw new ApiException("Le nom de la marque est obligatoire", HttpStatus.BAD_REQUEST);
            }

            Marque existingMarque = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Marque non trouvée avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            Marque entity = mapper.toEntity(request);
            entity.setId(existingMarque.getId());
            entity.setTrackingId(trackingId);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la mise à jour de la marque: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MarqueResponse findById(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Marque entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Marque non trouvée avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la recherche de la marque: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<MarqueResponse> findAll() {
        try {
            List<Marque> entities = repository.findAllOrdered();
            return entities.stream()
                .map(mapper::toResponse)
                .toList();
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération des marques: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Marque entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Marque non trouvée avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            repository.delete(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la suppression de la marque: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MarqueResponse findByNom(String nom) {
        try {
            Marque entity = repository.findByNom(nom)
                .orElseThrow(() -> new ApiException("Marque non trouvée avec nom: " + nom, HttpStatus.NOT_FOUND));
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la recherche de la marque: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}