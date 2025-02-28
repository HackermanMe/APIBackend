package com.api.backend.mapper;

import com.api.backend.dto.request.AppointmentRequest;
import com.api.backend.dto.response.AppointmentResponse;
import com.api.backend.dto.response.UserResponse;
import com.api.backend.entity.Appointment;
import com.api.backend.entity.User;
import com.api.backend.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    private final UserRepository clientRepository;
    private final UserRepository agentRepository;

    public AppointmentMapper(UserRepository clientRepository, UserRepository agentRepository) {
        this.clientRepository = clientRepository;
        this.agentRepository = agentRepository;
    }

    public Appointment toEntity(AppointmentRequest request) {
        if (request == null) {
            return null;
        }
        
        Appointment entity = new Appointment();
        entity.setTrackingId(UUID.randomUUID());
        if (request.getClientId() != null) {
            entity.setClient(clientRepository.findByTrackingId(request.getClientId())
                .orElseThrow(() -> new RuntimeException("User non trouvé avec trackingId: " + request.getClientId())));
        }
        if (request.getAgentId() != null) {
            entity.setAgent(agentRepository.findByTrackingId(request.getAgentId())
                .orElseThrow(() -> new RuntimeException("User non trouvé avec trackingId: " + request.getAgentId())));
        }
        entity.setDateTime(request.getDatetime());
        entity.setStatus(request.getStatus());
        entity.setNotes(request.getNotes());
        entity.setItemId(request.getItemid());
        entity.setItemType(request.getItemtype());
        return entity;
    }
    
    public AppointmentResponse toResponse(Appointment entity) {
        if (entity == null) {
            return null;
        }
        
        AppointmentResponse response = new AppointmentResponse();
        response.setTrackingid(entity.getTrackingId());
        if (entity.getClient() != null) {
            UserResponse r = UserResponse.fromUser(entity.getClient());
            response.setClient(r);
        }
        if (entity.getAgent() != null) {
            UserResponse r = UserResponse.fromUser(entity.getAgent());
            response.setAgent(r);
        }
        response.setDatetime(entity.getDateTime());
        response.setStatus(entity.getStatus());
        response.setNotes(entity.getNotes());
        response.setItemid(entity.getItemId());
        response.setItemtype(entity.getItemType());
        return response;
    }
}