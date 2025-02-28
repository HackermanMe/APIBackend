package com.api.backend.controller;

import com.api.backend.dto.request.PublicationRequest;
import com.api.backend.dto.response.PublicationResponse;
import com.api.backend.service.PublicationService;
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
@RequestMapping("/api/v1/publications")
@Tag(name = "Publications", description = "API de gestion des publications")
public class PublicationController {

    private final PublicationService service;

    public PublicationController(PublicationService service) {
        this.service = service;
    }

    @Operation(summary = "Créer une publication", description = "Crée une nouvelle publication dans le système")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Publication créée avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<GlobalResponse<PublicationResponse>> create(@RequestBody PublicationRequest request) {
        PublicationResponse response = service.create(request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Créé avec succès", response));
    }

    @Operation(summary = "Mettre à jour une publication", description = "Met à jour une publication existante par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Publication mise à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Publication non trouvée"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<PublicationResponse>> update(
            @Parameter(description = "TrackingId de la publication à mettre à jour") @PathVariable UUID trackingId,
            @RequestBody PublicationRequest request) {
        PublicationResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Mis à jour avec succès", response));
    }

    @Operation(summary = "Rechercher une publication", description = "Trouve une publication par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Publication trouvée avec succès"),
        @ApiResponse(responseCode = "404", description = "Publication non trouvée"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<PublicationResponse>> findById(
            @Parameter(description = "TrackingId de la publication à rechercher") @PathVariable UUID trackingId) {
        PublicationResponse response = service.findById(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Trouvé avec succès", response));
    }

    @Operation(summary = "Lister toutes les publications", description = "Récupère la liste de toutes les publications")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des publications récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<GlobalResponse<List<PublicationResponse>>> findAll() {
        List<PublicationResponse> responses = service.findAll();
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Récupéré tous avec succès", responses));
    }

    @Operation(summary = "Supprimer une publication", description = "Supprime une publication par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Publication supprimée avec succès"),
        @ApiResponse(responseCode = "404", description = "Publication non trouvée"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<Void>> delete(
            @Parameter(description = "TrackingId de la publication à supprimer") @PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Supprimé avec succès", null));
    }
}