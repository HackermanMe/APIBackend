package com.api.backend.controller;

import com.api.backend.dto.request.ModeleRequest;
import com.api.backend.dto.response.ModeleResponse;
import com.api.backend.service.ModeleService;
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
@RequestMapping("/api/v1/modeles")
@Tag(name = "Modèles", description = "API de gestion des modèles de voitures")
public class ModeleController {

    private final ModeleService service;

    public ModeleController(ModeleService service) {
        this.service = service;
    }

    @Operation(summary = "Créer un modèle", description = "Crée un nouveau modèle de voiture dans le système")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Modèle créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<GlobalResponse<ModeleResponse>> create(@RequestBody ModeleRequest request) {
        ModeleResponse response = service.create(request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Créé avec succès", response));
    }

    @Operation(summary = "Mettre à jour un modèle", description = "Met à jour un modèle existant par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Modèle mis à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Modèle non trouvé"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<ModeleResponse>> update(
            @Parameter(description = "TrackingId du modèle à mettre à jour") @PathVariable UUID trackingId,
            @RequestBody ModeleRequest request) {
        ModeleResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Mis à jour avec succès", response));
    }

    @Operation(summary = "Rechercher un modèle", description = "Trouve un modèle par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Modèle trouvé avec succès"),
        @ApiResponse(responseCode = "404", description = "Modèle non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<ModeleResponse>> findById(
            @Parameter(description = "TrackingId du modèle à rechercher") @PathVariable UUID trackingId) {
        ModeleResponse response = service.findById(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Trouvé avec succès", response));
    }

    @Operation(summary = "Lister tous les modèles", description = "Récupère la liste de tous les modèles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des modèles récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<GlobalResponse<List<ModeleResponse>>> findAll() {
        List<ModeleResponse> responses = service.findAll();
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Récupéré tous avec succès", responses));
    }

    @Operation(summary = "Supprimer un modèle", description = "Supprime un modèle par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Modèle supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Modèle non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<Void>> delete(
            @Parameter(description = "TrackingId du modèle à supprimer") @PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Supprimé avec succès", null));
    }

    @Operation(summary = "Rechercher un modèle par nom et marqueId", description = "Trouve un modèle par son nom et marqueId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Modèle trouvé avec succès"),
        @ApiResponse(responseCode = "404", description = "Modèle non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/nom/{nom}/marque/tracking/{marqueId}")
    public ResponseEntity<GlobalResponse<ModeleResponse>> findByNomAndMarqueId(@PathVariable String nom, @PathVariable UUID marqueId) {
        ModeleResponse response = service.findByNomAndMarqueId(nom, marqueId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Trouvé avec succès", response));
    }
}
