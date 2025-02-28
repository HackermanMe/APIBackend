package com.api.backend.service.impl;

import com.api.backend.dto.request.AppointmentRequest;
import com.api.backend.dto.response.AppointmentResponse;
import com.api.backend.entity.Appointment;
import com.api.backend.entity.User;
import com.api.backend.mapper.AppointmentMapper;
import com.api.backend.repository.AppointmentRepository;
import com.api.backend.repository.UserRepository;
import com.api.backend.service.AppointmentService;
import com.api.backend.utils.exception.ApiException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository repository;
    private final AppointmentMapper mapper;
    private final UserRepository userRepository;

    public AppointmentServiceImpl(AppointmentRepository repository, AppointmentMapper mapper, UserRepository userRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Override
    public AppointmentResponse create(AppointmentRequest request) {
        try {
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getDatetime() == null) {
                throw new ApiException("La date et l'heure du rendez-vous sont obligatoires", HttpStatus.BAD_REQUEST);
            }
            if (request.getDatetime().isBefore(LocalDateTime.now())) {
                throw new ApiException("La date du rendez-vous ne peut pas être dans le passé", HttpStatus.BAD_REQUEST);
            }
            if (request.getClientId() == null) {
                throw new ApiException("L'ID du client est obligatoire", HttpStatus.BAD_REQUEST);
            }
            if (request.getAgentId() == null) {
                throw new ApiException("L'ID de l'agent est obligatoire", HttpStatus.BAD_REQUEST);
            }

            // Récupérer l'agent par son UUID
            User agent = userRepository.findByTrackingId(request.getAgentId())
                .orElseThrow(() -> new ApiException("Agent non trouvé", HttpStatus.NOT_FOUND));

            if (repository.existsByAgentIdAndDateTime(agent.getId(), request.getDatetime())) {
                throw new ApiException("L'agent a déjà un rendez-vous à cette date et heure", HttpStatus.CONFLICT);
            }

            Appointment entity = mapper.toEntity(request);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la création du rendez-vous: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AppointmentResponse update(UUID trackingId, AppointmentRequest request) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }
            if (request == null) {
                throw new ApiException("La requête ne peut pas être nulle", HttpStatus.BAD_REQUEST);
            }

            // Validations métier
            if (request.getDatetime() == null) {
                throw new ApiException("La date et l'heure du rendez-vous sont obligatoires", HttpStatus.BAD_REQUEST);
            }
            if (request.getDatetime().isBefore(LocalDateTime.now())) {
                throw new ApiException("La date du rendez-vous ne peut pas être dans le passé", HttpStatus.BAD_REQUEST);
            }

            Appointment existingAppointment = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Rendez-vous non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));

            // Récupérer l'agent par son UUID
            User agent = userRepository.findByTrackingId(request.getAgentId())
                .orElseThrow(() -> new ApiException("Agent non trouvé", HttpStatus.NOT_FOUND));

            if (!existingAppointment.getDateTime().equals(request.getDatetime()) &&
                repository.existsByAgentIdAndDateTime(agent.getId(), request.getDatetime())) {
                throw new ApiException("L'agent a déjà un rendez-vous à cette date et heure", HttpStatus.CONFLICT);
            }

            Appointment entity = mapper.toEntity(request);
            entity.setId(existingAppointment.getId());
            entity.setTrackingId(trackingId);
            entity = repository.save(entity);
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la mise à jour du rendez-vous: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AppointmentResponse findById(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Appointment entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Rendez-vous non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            return mapper.toResponse(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la recherche du rendez-vous: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<AppointmentResponse> findAll() {
        try {
            List<Appointment> entities = repository.findAllOrdered();
            return entities.stream()
                .map(mapper::toResponse)
                .toList();
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la récupération des rendez-vous: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(UUID trackingId) {
        try {
            if (trackingId == null) {
                throw new ApiException("L'ID de suivi ne peut pas être nul", HttpStatus.BAD_REQUEST);
            }

            Appointment entity = repository.findByTrackingId(trackingId)
                .orElseThrow(() -> new ApiException("Rendez-vous non trouvé avec trackingId: " + trackingId, 
                    HttpStatus.NOT_FOUND));
            repository.delete(entity);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException("Erreur lors de la suppression du rendez-vous: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}