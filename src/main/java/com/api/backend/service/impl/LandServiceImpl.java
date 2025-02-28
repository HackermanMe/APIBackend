package com.api.backend.service.impl;

import com.api.backend.dto.request.LandRequest;
import com.api.backend.dto.response.LandResponse;
import com.api.backend.entity.Land;
import com.api.backend.enums.PublicationStatus;
import com.api.backend.mapper.LandMapper;
import com.api.backend.repository.LandRepository;
import com.api.backend.service.LandService;
import com.api.backend.utils.exception.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class LandServiceImpl implements LandService {

    private final LandRepository repository;
    private final LandMapper mapper;

    public LandServiceImpl(LandRepository repository, LandMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public LandResponse create(LandRequest request) {
        try {
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getSurface() <= 0) {
                throw new ApiException("La surface doit être supérieure à 0", HttpStatus.BAD_REQUEST);
            }
            if (request.getPrix() == null || request.getPrix().doubleValue() <= 0) {
                throw new ApiException("Le prix doit être supérieur à 0", HttpStatus.BAD_REQUEST);
            }

            Land entity = mapper.toEntity(request);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la création du terrain: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public LandResponse update(UUID trackingId, LandRequest request) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getSurface() <= 0) {
                throw new ApiException("La surface doit être supérieure à 0", HttpStatus.BAD_REQUEST);
            }
            if (request.getPrix() == null || request.getPrix().doubleValue() <= 0) {
                throw new ApiException("Le prix doit être supérieur à 0", HttpStatus.BAD_REQUEST);
            }

            Land existingLand = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Terrain non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            Land entity = mapper.toEntity(request);
            entity.setId(existingLand.getId());
            entity.setTrackingId(trackingId);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la mise à jour du terrain: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public LandResponse findById(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            // Charger le terrain avec le propriétaire
            Land entity = repository.findByTrackingIdWithProprietaire(trackingId)
                .orElseThrow(() -> new ApiException("Terrain non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            // Charger les collections séparément
            repository.findByTrackingIdWithCaracteristiques(trackingId)
                .ifPresent(l -> entity.setCaracteristiques(l.getCaracteristiques()));
            
            repository.findByTrackingIdWithImages(trackingId)
                .ifPresent(l -> entity.setImages(l.getImages()));
            
            repository.findByTrackingIdWithReviews(trackingId)
                .ifPresent(l -> entity.setReviews(l.getReviews()));

            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la recherche du terrain: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<LandResponse> findAll() {
        try {
            List<Land> entities = repository.findAllOrdered();
            return entities.stream()
                .map(land -> {
                    // Charger les collections séparément pour chaque terrain
                    repository.findByTrackingIdWithCaracteristiques(land.getTrackingId())
                        .ifPresent(l -> land.setCaracteristiques(l.getCaracteristiques()));
                    
                    repository.findByTrackingIdWithImages(land.getTrackingId())
                        .ifPresent(l -> land.setImages(l.getImages()));
                    
                    repository.findByTrackingIdWithReviews(land.getTrackingId())
                        .ifPresent(l -> land.setReviews(l.getReviews()));

                    return mapper.toResponse(land);
                })
                .toList();
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération des terrains: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Land entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Terrain non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            repository.delete(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la suppression du terrain: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public LandResponse publish(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Land entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Terrain non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            // Vérifier que toutes les informations requises sont présentes
            if (entity.getTitre() == null || entity.getTitre().isEmpty() ||
                entity.getDescription() == null || entity.getDescription().isEmpty() ||
                entity.getSurface() <= 0 || entity.getPrix() == null || entity.getPrix().doubleValue() <= 0) {
                throw new ApiException("Les informations obligatoires sont manquantes", HttpStatus.BAD_REQUEST);
            }

            entity.setStatus(PublicationStatus.PUBLIE);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la publication du terrain: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public LandResponse archive(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Land entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Terrain non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            entity.setStatus(PublicationStatus.ARCHIVE);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de l'archivage du terrain: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}