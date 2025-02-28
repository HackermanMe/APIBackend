package com.api.backend.mapper;

import com.api.backend.dto.request.PublicationRequest;
import com.api.backend.dto.response.CarResponse;
import com.api.backend.dto.response.HouseResponse;
import com.api.backend.dto.response.LandResponse;
import com.api.backend.dto.response.PublicationResponse;
import com.api.backend.dto.response.UserResponse;
import com.api.backend.entity.Car;
import com.api.backend.entity.House;
import com.api.backend.entity.Land;
import com.api.backend.entity.Publication;
import com.api.backend.entity.User;
import com.api.backend.repository.CarRepository;
import com.api.backend.repository.HouseRepository;
import com.api.backend.repository.LandRepository;
import com.api.backend.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class PublicationMapper {
    private final UserRepository utilisateurRepository;
    private final CarRepository voitureRepository;
    private final HouseRepository maisonRepository;
    private final LandRepository terrainRepository;

    public PublicationMapper(UserRepository utilisateurRepository, CarRepository voitureRepository, HouseRepository maisonRepository, LandRepository terrainRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.voitureRepository = voitureRepository;
        this.maisonRepository = maisonRepository;
        this.terrainRepository = terrainRepository;
    }

    public Publication toEntity(PublicationRequest request) {
        if (request == null) {
            return null;
        }
        
        Publication entity = new Publication();
        entity.setTrackingId(UUID.randomUUID());
        if (request.getUtilisateurId() != null) {
            entity.setUtilisateur(utilisateurRepository.findByTrackingId(request.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("User non trouvé avec trackingId: " + request.getUtilisateurId())));
        }
        entity.setTitre(request.getTitre());
        entity.setContenu(request.getContenu());
        entity.setItemId(request.getItemid());
        entity.setTypeItem(request.getTypeitem());
        entity.setStatut(request.getStatut());
        entity.setEnVedette(request.getEnvedette());
        if (request.getVoitureId() != null) {
            entity.setVoiture(voitureRepository.findByTrackingId(request.getVoitureId())
                .orElseThrow(() -> new RuntimeException("Car non trouvé avec trackingId: " + request.getVoitureId())));
        }
        if (request.getMaisonId() != null) {
            entity.setMaison(maisonRepository.findByTrackingId(request.getMaisonId())
                .orElseThrow(() -> new RuntimeException("House non trouvé avec trackingId: " + request.getMaisonId())));
        }
        if (request.getTerrainId() != null) {
            entity.setTerrain(terrainRepository.findByTrackingId(request.getTerrainId())
                .orElseThrow(() -> new RuntimeException("Land non trouvé avec trackingId: " + request.getTerrainId())));
        }
        return entity;
    }
    
    public PublicationResponse toResponse(Publication entity) {
        if (entity == null) {
            return null;
        }
        
        PublicationResponse response = new PublicationResponse();
        response.setTrackingid(entity.getTrackingId());
        if (entity.getUtilisateur() != null) {
            UserResponse r = UserResponse.fromUser(entity.getUtilisateur());
            response.setUtilisateur(r);
        }
        response.setTitre(entity.getTitre());
        response.setContenu(entity.getContenu());
        response.setItemid(entity.getItemId());
        response.setTypeitem(entity.getTypeItem());
        response.setStatut(entity.getStatut());
        response.setEnvedette(entity.isEnVedette());
        if (entity.getVoiture() != null) {
            CarResponse r = new CarResponse();
            r.setTrackingid(entity.getVoiture().getTrackingId());
            response.setVoiture(r);
        }
        if (entity.getMaison() != null) {
            HouseResponse r = new HouseResponse();
            r.setTrackingid(entity.getMaison().getTrackingId());
            response.setMaison(r);
        }
        if (entity.getTerrain() != null) {
            LandResponse r = new LandResponse();
            r.setTrackingId(entity.getTerrain().getTrackingId());
            response.setTerrain(r);
        }
        return response;
    }
}