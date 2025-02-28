package com.api.backend.service.impl;

import com.api.backend.dto.request.ModeleRequest;
import com.api.backend.dto.response.ModeleResponse;
import com.api.backend.entity.Modele;
import com.api.backend.mapper.ModeleMapper;
import com.api.backend.repository.ModeleRepository;
import com.api.backend.service.ModeleService;
import com.api.backend.utils.exception.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ModeleServiceImpl implements ModeleService {

    private final ModeleRepository repository;
    private final ModeleMapper mapper;

    public ModeleServiceImpl(ModeleRepository repository, ModeleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ModeleResponse create(ModeleRequest request) {
        try {
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getNom() == null || request.getNom().trim().isEmpty()) {
                throw new ApiException("Le nom du modèle est obligatoire", HttpStatus.BAD_REQUEST);
            }

            Modele entity = mapper.toEntity(request);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la création du modèle: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ModeleResponse update(UUID trackingId, ModeleRequest request) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getNom() == null || request.getNom().trim().isEmpty()) {
                throw new ApiException("Le nom du modèle est obligatoire", HttpStatus.BAD_REQUEST);
            }

            Modele existingModele = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Modèle non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            Modele entity = mapper.toEntity(request);
            entity.setId(existingModele.getId());
            entity.setTrackingId(trackingId);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la mise à jour du modèle: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ModeleResponse findById(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Modele entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Modèle non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la recherche du modèle: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<ModeleResponse> findAll() {
        try {
            List<Modele> entities = repository.findAllOrdered();
            return entities.stream()
                .map(mapper::toResponse)
                .toList();
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération des modèles: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Modele entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Modèle non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            repository.delete(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la suppression du modèle: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ModeleResponse findByNomAndMarqueId(String nom, UUID marqueId) {
        try {
            Modele entity = repository.findByNomAndMarqueId(nom, marqueId)
                .orElseThrow(() -> new ApiException("Modèle non trouvé avec nom: " + nom + " et marqueId: " + marqueId, HttpStatus.NOT_FOUND));
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la recherche du modèle: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        }

    @Override
    public ModeleResponse findByNom(String nom) {
        try {
            Modele entity = repository.findByNom(nom)
                .orElseThrow(() -> new ApiException("Modèle non trouvé avec nom: " + nom, HttpStatus.NOT_FOUND));
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la recherche du modèle: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}