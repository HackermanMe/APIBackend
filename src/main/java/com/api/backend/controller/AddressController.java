package com.api.backend.controller;

import com.api.backend.dto.request.AddressRequest;
import com.api.backend.dto.response.AddressResponse;
import com.api.backend.service.AddressService;
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
@RequestMapping("/api/v1/addresses")
@Tag(name = "Adresses", description = "API de gestion des adresses")
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @Operation(summary = "Créer une adresse", description = "Crée une nouvelle adresse dans le système")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Adresse créée avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<GlobalResponse<AddressResponse>> create(@RequestBody AddressRequest request) {
        AddressResponse response = service.create(request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Créé avec succès", response));
    }

    @Operation(summary = "Mettre à jour une adresse", description = "Met à jour une adresse existante par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Adresse mise à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Adresse non trouvée"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<AddressResponse>> update(
            @Parameter(description = "TrackingId de l'adresse à mettre à jour") @PathVariable UUID trackingId,
            @RequestBody AddressRequest request) {
        AddressResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Mis à jour avec succès", response));
    }

    @Operation(summary = "Rechercher une adresse", description = "Trouve une adresse par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Adresse trouvée avec succès"),
        @ApiResponse(responseCode = "404", description = "Adresse non trouvée"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<AddressResponse>> findById(
            @Parameter(description = "TrackingId de l'adresse à rechercher") @PathVariable UUID trackingId) {
        AddressResponse response = service.findById(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Trouvé avec succès", response));
    }

    @Operation(summary = "Lister toutes les adresses", description = "Récupère la liste de toutes les adresses")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des adresses récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<GlobalResponse<List<AddressResponse>>> findAll() {
        List<AddressResponse> responses = service.findAll();
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Récupéré tous avec succès", responses));
    }

    @Operation(summary = "Supprimer une adresse", description = "Supprime une adresse par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Adresse supprimée avec succès"),
        @ApiResponse(responseCode = "404", description = "Adresse non trouvée"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<Void>> delete(
            @Parameter(description = "TrackingId de l'adresse à supprimer") @PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Supprimé avec succès", null));
    }
}