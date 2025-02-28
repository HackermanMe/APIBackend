package com.api.backend.controller;

import com.api.backend.dto.request.FavoriteRequest;
import com.api.backend.dto.response.FavoriteResponse;
import com.api.backend.service.FavoriteService;
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
@RequestMapping("/api/v1/favorites")
@Tag(name = "Favoris", description = "API de gestion des favoris")
public class FavoriteController {

    private final FavoriteService service;

    public FavoriteController(FavoriteService service) {
        this.service = service;
    }

    @Operation(summary = "Créer un favori", description = "Crée un nouveau favori dans le système")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Favori créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<GlobalResponse<FavoriteResponse>> create(@RequestBody FavoriteRequest request) {
        FavoriteResponse response = service.create(request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Créé avec succès", response));
    }

    @Operation(summary = "Mettre à jour un favori", description = "Met à jour un favori existant par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Favori mis à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Favori non trouvé"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<FavoriteResponse>> update(
            @Parameter(description = "TrackingId du favori à mettre à jour") @PathVariable UUID trackingId,
            @RequestBody FavoriteRequest request) {
        FavoriteResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Mis à jour avec succès", response));
    }

    @Operation(summary = "Rechercher un favori", description = "Trouve un favori par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Favori trouvé avec succès"),
        @ApiResponse(responseCode = "404", description = "Favori non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<FavoriteResponse>> findById(
            @Parameter(description = "TrackingId du favori à rechercher") @PathVariable UUID trackingId) {
        FavoriteResponse response = service.findById(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Trouvé avec succès", response));
    }

    @Operation(summary = "Lister tous les favoris", description = "Récupère la liste de tous les favoris")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des favoris récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<GlobalResponse<List<FavoriteResponse>>> findAll() {
        List<FavoriteResponse> responses = service.findAll();
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Récupéré tous avec succès", responses));
    }

    @Operation(summary = "Supprimer un favori", description = "Supprime un favori par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Favori supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Favori non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<Void>> delete(
            @Parameter(description = "TrackingId du favori à supprimer") @PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Supprimé avec succès", null));
    }
}