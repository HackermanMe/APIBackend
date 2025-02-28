package com.api.backend.service.impl;

import com.api.backend.dto.request.CarRequest;
import com.api.backend.dto.response.CarResponse;
import com.api.backend.entity.Car;
import com.api.backend.mapper.CarMapper;
import com.api.backend.repository.CarRepository;
import com.api.backend.service.CarService;
import com.api.backend.utils.exception.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {

    private final CarRepository repository;
    private final CarMapper mapper;

    public CarServiceImpl(CarRepository repository, CarMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public CarResponse create(CarRequest request) {
        try {
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getKilometrage() < 0) {
                throw new ApiException("Le kilométrage ne peut pas être négatif", HttpStatus.BAD_REQUEST);
            }
            if (request.getPrix() == null || request.getPrix().doubleValue() <= 0) {
                throw new ApiException("Le prix doit être supérieur à 0", HttpStatus.BAD_REQUEST);
            }
            if (request.getAnnee() == null || request.getAnnee() < 1900) {
                throw new ApiException("L'année doit être valide", HttpStatus.BAD_REQUEST);
            }

            Car entity = mapper.toEntity(request);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la création de la voiture: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public CarResponse update(UUID trackingId, CarRequest request) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getKilometrage() < 0) {
                throw new ApiException("Le kilométrage ne peut pas être négatif", HttpStatus.BAD_REQUEST);
            }
            if (request.getPrix() == null || request.getPrix().doubleValue() <= 0) {
                throw new ApiException("Le prix doit être supérieur à 0", HttpStatus.BAD_REQUEST);
            }
            if (request.getAnnee() == null || request.getAnnee() < 1900) {
                throw new ApiException("L'année doit être valide", HttpStatus.BAD_REQUEST);
            }

            Car existingCar = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Voiture non trouvée avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            Car entity = mapper.toEntity(request);
            entity.setId(existingCar.getId());
            entity.setTrackingId(trackingId);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la mise à jour de la voiture: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CarResponse findById(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            // Charger la voiture avec le modèle et la marque
            Car entity = repository.findByTrackingIdWithModele(trackingId)
                .orElseThrow(() -> new ApiException("Voiture non trouvée avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            // Charger les collections séparément
            repository.findByTrackingIdWithCaracteristiques(trackingId)
                .ifPresent(c -> entity.setCaracteristiques(c.getCaracteristiques()));
            
            repository.findByTrackingIdWithImages(trackingId)
                .ifPresent(c -> entity.setImages(c.getImages()));
            
            repository.findByTrackingIdWithReviews(trackingId)
                .ifPresent(c -> entity.setReviews(c.getReviews()));

            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la recherche de la voiture: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<CarResponse> findAll() {
        try {
            List<Car> entities = repository.findAllOrdered();
            
            // Charger les collections pour chaque voiture
            for (Car car : entities) {
                repository.findByIdWithCaracteristiques(car.getId())
                    .ifPresent(c -> car.setCaracteristiques(c.getCaracteristiques()));
                
                repository.findByIdWithImages(car.getId())
                    .ifPresent(c -> car.setImages(c.getImages()));
                
                repository.findByIdWithReviews(car.getId())
                    .ifPresent(c -> car.setReviews(c.getReviews()));
            }
            
            return entities.stream()
                .map(mapper::toResponse)
                .toList();
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération des voitures: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public void delete(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Car entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Voiture non trouvée avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            repository.delete(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la suppression de la voiture: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}