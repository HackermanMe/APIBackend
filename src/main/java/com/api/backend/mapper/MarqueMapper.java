package com.api.backend.mapper;

import com.api.backend.dto.request.MarqueRequest;
import com.api.backend.dto.response.MarqueResponse;
import com.api.backend.entity.Marque;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class MarqueMapper {

    public Marque toEntity(MarqueRequest request) {
        if (request == null) {
            return null;
        }
        
        Marque entity = new Marque();
        entity.setTrackingId(UUID.randomUUID());
        entity.setNom(request.getNom());
        return entity;
    }
    
    public MarqueResponse toResponse(Marque entity) {
        if (entity == null) {
            return null;
        }
        
        MarqueResponse response = new MarqueResponse();
        response.setTrackingid(entity.getTrackingId());
        response.setNom(entity.getNom());
        return response;
    }
}