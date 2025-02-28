package com.api.backend.service.impl;

import com.api.backend.dto.request.HouseRequest;
import com.api.backend.dto.response.HouseResponse;
import com.api.backend.entity.House;
import com.api.backend.mapper.HouseMapper;
import com.api.backend.repository.HouseRepository;
import com.api.backend.service.HouseService;
import com.api.backend.utils.exception.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class HouseServiceImpl implements HouseService {

    private final HouseRepository repository;
    private final HouseMapper mapper;

    public HouseServiceImpl(HouseRepository repository, HouseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public HouseResponse create(HouseRequest request) {
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

            House entity = mapper.toEntity(request);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la création de la maison: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public HouseResponse update(UUID trackingId, HouseRequest request) {
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

            House existingHouse = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Maison non trouvée avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            House entity = mapper.toEntity(request);
            entity.setId(existingHouse.getId());
            entity.setTrackingId(trackingId);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la mise à jour de la maison: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public HouseResponse findById(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            // Charger la maison avec le propriétaire
            House entity = repository.findByTrackingIdWithProprietaire(trackingId)
                .orElseThrow(() -> new ApiException("Maison non trouvée avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            // Charger les collections séparément
            repository.findByTrackingIdWithCaracteristiques(trackingId)
                .ifPresent(h -> entity.setCaracteristiques(h.getCaracteristiques()));
            
            repository.findByTrackingIdWithImages(trackingId)
                .ifPresent(h -> entity.setImages(h.getImages()));
            
            repository.findByTrackingIdWithReviews(trackingId)
                .ifPresent(h -> entity.setReviews(h.getReviews()));

            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la recherche de la maison: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<HouseResponse> findAll() {
        try {
            List<House> entities = repository.findAllOrdered();
            return entities.stream()
                .map(house -> {
                    // Charger les collections séparément pour chaque maison
                    repository.findByTrackingIdWithCaracteristiques(house.getTrackingId())
                        .ifPresent(h -> house.setCaracteristiques(h.getCaracteristiques()));
                    
                    repository.findByTrackingIdWithImages(house.getTrackingId())
                        .ifPresent(h -> house.setImages(h.getImages()));
                    
                    repository.findByTrackingIdWithReviews(house.getTrackingId())
                        .ifPresent(h -> house.setReviews(h.getReviews()));

                    return mapper.toResponse(house);
                })
                .toList();
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération des maisons: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            House entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Maison non trouvée avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            repository.delete(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la suppression de la maison: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}