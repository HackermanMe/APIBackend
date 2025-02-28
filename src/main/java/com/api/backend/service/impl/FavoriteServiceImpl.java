package com.api.backend.service.impl;

import com.api.backend.dto.request.FavoriteRequest;
import com.api.backend.dto.response.FavoriteResponse;
import com.api.backend.entity.Favorite;
import com.api.backend.mapper.FavoriteMapper;
import com.api.backend.repository.FavoriteRepository;
import com.api.backend.service.FavoriteService;
import com.api.backend.utils.exception.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository repository;
    private final FavoriteMapper mapper;

    public FavoriteServiceImpl(FavoriteRepository repository, FavoriteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public FavoriteResponse create(FavoriteRequest request) {
        try {
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getItemid() == null) {
                throw new ApiException("L'ID de l'item est obligatoire", HttpStatus.BAD_REQUEST);
            }
            if (request.getItemtype() == null) {
                throw new ApiException("Le type d'item est obligatoire", HttpStatus.BAD_REQUEST);
            }
            if (request.getUtilisateurId() == null) {
                throw new ApiException("L'ID de l'utilisateur est obligatoire", HttpStatus.BAD_REQUEST);
            }

            Favorite entity = mapper.toEntity(request);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la création du favori: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public FavoriteResponse update(UUID trackingId, FavoriteRequest request) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getItemid() == null) {
                throw new ApiException("L'ID de l'item est obligatoire", HttpStatus.BAD_REQUEST);
            }
            if (request.getItemtype() == null) {
                throw new ApiException("Le type d'item est obligatoire", HttpStatus.BAD_REQUEST);
            }
            if (request.getUtilisateurId() == null) {
                throw new ApiException("L'ID de l'utilisateur est obligatoire", HttpStatus.BAD_REQUEST);
            }

            Favorite existingFavorite = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Favori non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            Favorite entity = mapper.toEntity(request);
            entity.setId(existingFavorite.getId());
            entity.setTrackingId(trackingId);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la mise à jour du favori: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public FavoriteResponse findById(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Favorite entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Favori non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la recherche du favori: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<FavoriteResponse> findAll() {
        try {
            List<Favorite> entities = repository.findAllOrdered();
            return entities.stream()
                .map(mapper::toResponse)
                .toList();
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération des favoris: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Favorite entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Favori non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            repository.delete(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la suppression du favori: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}