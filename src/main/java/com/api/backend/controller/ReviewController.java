package com.api.backend.controller;

import com.api.backend.dto.request.ReviewRequest;
import com.api.backend.dto.response.ReviewResponse;
import com.api.backend.service.ReviewService;
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
@RequestMapping("/api/v1/reviews")
@Tag(name = "Avis", description = "API de gestion des avis")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @Operation(summary = "Créer un avis", description = "Crée un nouvel avis dans le système")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avis créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<GlobalResponse<ReviewResponse>> create(@RequestBody ReviewRequest request) {
        ReviewResponse response = service.create(request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Créé avec succès", response));
    }

    @Operation(summary = "Mettre à jour un avis", description = "Met à jour un avis existant par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avis mis à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Avis non trouvé"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<ReviewResponse>> update(
            @Parameter(description = "TrackingId de l'avis à mettre à jour") @PathVariable UUID trackingId,
            @RequestBody ReviewRequest request) {
        ReviewResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Mis à jour avec succès", response));
    }

    @Operation(summary = "Rechercher un avis", description = "Trouve un avis par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avis trouvé avec succès"),
        @ApiResponse(responseCode = "404", description = "Avis non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<ReviewResponse>> findById(
            @Parameter(description = "TrackingId de l'avis à rechercher") @PathVariable UUID trackingId) {
        ReviewResponse response = service.findById(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Trouvé avec succès", response));
    }

    @Operation(summary = "Lister tous les avis", description = "Récupère la liste de tous les avis")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des avis récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<GlobalResponse<List<ReviewResponse>>> findAll() {
        List<ReviewResponse> responses = service.findAll();
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Récupéré tous avec succès", responses));
    }

    @Operation(summary = "Supprimer un avis", description = "Supprime un avis par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Avis supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Avis non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<Void>> delete(
            @Parameter(description = "TrackingId de l'avis à supprimer") @PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Supprimé avec succès", null));
    }
}