package com.api.backend.service.impl;

import com.api.backend.dto.request.AddressRequest;
import com.api.backend.dto.response.AddressResponse;
import com.api.backend.entity.Address;
import com.api.backend.mapper.AddressMapper;
import com.api.backend.repository.AddressRepository;
import com.api.backend.service.AddressService;
import com.api.backend.utils.exception.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final AddressMapper mapper;

    public AddressServiceImpl(AddressRepository repository, AddressMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AddressResponse create(AddressRequest request) {
        try {
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getVille() == null || request.getVille().trim().isEmpty()) {
                throw new ApiException("La ville est obligatoire", HttpStatus.BAD_REQUEST);
            }
            if (request.getDistrict() == null || request.getDistrict().trim().isEmpty()) {
                throw new ApiException("Le district est obligatoire", HttpStatus.BAD_REQUEST);
            }

            Address entity = mapper.toEntity(request);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la création de l'adresse: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AddressResponse update(UUID trackingId, AddressRequest request) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getVille() == null || request.getVille().trim().isEmpty()) {
                throw new ApiException("La ville est obligatoire", HttpStatus.BAD_REQUEST);
            }
            if (request.getDistrict() == null || request.getDistrict().trim().isEmpty()) {
                throw new ApiException("Le district est obligatoire", HttpStatus.BAD_REQUEST);
            }

            Address existingAddress = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Adresse non trouvée avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            Address entity = mapper.toEntity(request);
            entity.setId(existingAddress.getId());
            entity.setTrackingId(trackingId);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la mise à jour de l'adresse: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AddressResponse findById(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Address entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Adresse non trouvée avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la recherche de l'adresse: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<AddressResponse> findAll() {
        try {
            List<Address> entities = repository.findAllOrdered();
            return entities.stream()
                .map(mapper::toResponse)
                .toList();
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération des adresses: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Address entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Adresse non trouvée avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            repository.delete(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la suppression de l'adresse: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}