package com.api.backend.controller;

import com.api.backend.dto.request.MarqueRequest;
import com.api.backend.dto.response.MarqueResponse;
import com.api.backend.service.MarqueService;
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
@RequestMapping("/api/v1/marques")
@Tag(name = "Marques", description = "API de gestion des marques de voitures")
public class MarqueController {

    private final MarqueService service;

    public MarqueController(MarqueService service) {
        this.service = service;
    }

    @Operation(summary = "Créer une marque", description = "Crée une nouvelle marque de voiture dans le système")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Marque créée avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<GlobalResponse<MarqueResponse>> create(@RequestBody MarqueRequest request) {
        MarqueResponse response = service.create(request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Créé avec succès", response));
    }

    @Operation(summary = "Mettre à jour une marque", description = "Met à jour une marque existante par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Marque mise à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Marque non trouvée"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<MarqueResponse>> update(
            @Parameter(description = "TrackingId de la marque à mettre à jour") @PathVariable UUID trackingId,
            @RequestBody MarqueRequest request) {
        MarqueResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Mis à jour avec succès", response));
    }

    @Operation(summary = "Rechercher une marque", description = "Trouve une marque par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Marque trouvée avec succès"),
        @ApiResponse(responseCode = "404", description = "Marque non trouvée"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<MarqueResponse>> findById(
            @Parameter(description = "TrackingId de la marque à rechercher") @PathVariable UUID trackingId) {
        MarqueResponse response = service.findById(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Trouvé avec succès", response));
    }

    @Operation(summary = "Lister toutes les marques", description = "Récupère la liste de toutes les marques")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des marques récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<GlobalResponse<List<MarqueResponse>>> findAll() {
        List<MarqueResponse> responses = service.findAll();
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Récupéré tous avec succès", responses));
    }

    @Operation(summary = "Supprimer une marque", description = "Supprime une marque par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Marque supprimée avec succès"),
        @ApiResponse(responseCode = "404", description = "Marque non trouvée"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<Void>> delete(
            @Parameter(description = "TrackingId de la marque à supprimer") @PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Supprimé avec succès", null));
    }

    @Operation(summary = "Rechercher une marque par nom", description = "Trouve une marque par son nom")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Marque trouvée avec succès"),
        @ApiResponse(responseCode = "404", description = "Marque non trouvée"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/nom/{nom}")
    public ResponseEntity<GlobalResponse<MarqueResponse>> findByNom(@PathVariable String nom) {
        MarqueResponse response = service.findByNom(nom);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Trouvé avec succès", response));
    }
    
}