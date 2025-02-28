package com.api.backend.mapper;

import com.api.backend.dto.request.ModeleRequest;
import com.api.backend.dto.response.MarqueResponse;
import com.api.backend.dto.response.ModeleResponse;
import com.api.backend.entity.Marque;
import com.api.backend.entity.Modele;
import com.api.backend.repository.MarqueRepository;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ModeleMapper {
    private final MarqueRepository marqueRepository;

    public ModeleMapper(MarqueRepository marqueRepository) {
        this.marqueRepository = marqueRepository;
    }

    public Modele toEntity(ModeleRequest request) {
        if (request == null) {
            return null;
        }
        
        Modele entity = new Modele();
        entity.setTrackingId(UUID.randomUUID());
        entity.setNom(request.getNom());
        
        if (request.getMarqueId() != null) {
            Marque marque = marqueRepository.findByTrackingId(request.getMarqueId())
                .orElseThrow(() -> new RuntimeException("Marque non trouvée avec trackingId: " + request.getMarqueId()));
            entity.setMarque(marque);
        }
        return entity;
    }
    
    public ModeleResponse toResponse(Modele entity) {
        if (entity == null) {
            return null;
        }
        
        ModeleResponse response = new ModeleResponse();
        response.setTrackingid(entity.getTrackingId());
        response.setNom(entity.getNom());
        
        if (entity.getMarque() != null) {
            MarqueResponse marqueResponse = new MarqueResponse();
            marqueResponse.setTrackingid(entity.getMarque().getTrackingId());
            marqueResponse.setNom(entity.getMarque().getNom());
            response.setMarque(marqueResponse);
        }
        
        return response;
    }
}