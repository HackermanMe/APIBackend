package com.api.backend.controller;

import com.api.backend.dto.request.AppointmentRequest;
import com.api.backend.dto.response.AppointmentResponse;
import com.api.backend.service.AppointmentService;
import com.api.backend.utils.GlobalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.Date;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/appointments")
@Tag(name = "Rendez-vous", description = "API de gestion des rendez-vous")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @Operation(summary = "Créer un rendez-vous", description = "Crée un nouveau rendez-vous dans le système")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rendez-vous créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<GlobalResponse<AppointmentResponse>> create(@RequestBody AppointmentRequest request) {
        AppointmentResponse response = service.create(request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Créé avec succès", response));
    }

    @Operation(summary = "Mettre à jour un rendez-vous", description = "Met à jour un rendez-vous existant par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rendez-vous mis à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Rendez-vous non trouvé"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<AppointmentResponse>> update(
            @Parameter(description = "TrackingId du rendez-vous à mettre à jour") @PathVariable UUID trackingId,
            @RequestBody AppointmentRequest request) {
        AppointmentResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Mis à jour avec succès", response));
    }

    @Operation(summary = "Rechercher un rendez-vous", description = "Trouve un rendez-vous par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rendez-vous trouvé avec succès"),
        @ApiResponse(responseCode = "404", description = "Rendez-vous non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<AppointmentResponse>> findById(
            @Parameter(description = "TrackingId du rendez-vous à rechercher") @PathVariable UUID trackingId) {
        AppointmentResponse response = service.findById(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Trouvé avec succès", response));
    }

    @Operation(summary = "Lister tous les rendez-vous", description = "Récupère la liste de tous les rendez-vous")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des rendez-vous récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<GlobalResponse<List<AppointmentResponse>>> findAll() {
        List<AppointmentResponse> responses = service.findAll();
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Récupéré tous avec succès", responses));
    }

    @Operation(summary = "Supprimer un rendez-vous", description = "Supprime un rendez-vous par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Rendez-vous supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Rendez-vous non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<Void>> delete(
            @Parameter(description = "TrackingId du rendez-vous à supprimer") @PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Supprimé avec succès", null));
    }
}