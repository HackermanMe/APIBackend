package com.api.backend.controller;

import com.api.backend.dto.request.HouseRequest;
import com.api.backend.dto.response.HouseResponse;
import com.api.backend.service.HouseService;
import com.api.backend.utils.GlobalResponse;
import com.api.backend.utils.exception.ApiException;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.UUID;
import java.util.Date;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/houses")
@Tag(name = "Maisons", description = "API de gestion des maisons")
@Transactional
public class HouseController {

    private static final String UPLOAD_DIR = "src/main/resources/static/images";

    private final HouseService service;

    public HouseController(HouseService service) {
        this.service = service;
        // Créer le répertoire d'upload s'il n'existe pas
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        } catch (IOException e) {
            throw new RuntimeException("Impossible de créer le répertoire d'upload", e);
        }
    }

    @Operation(summary = "Créer une maison", description = "Crée une nouvelle maison dans le système")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Maison créée avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<GlobalResponse<HouseResponse>> create(
            @RequestParam("data") String houseData,
            @RequestParam(value = "images", required = false) List<MultipartFile> images) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            HouseRequest request = mapper.readValue(houseData, HouseRequest.class);
            
            // Si aucune image n'est fournie, initialiser une liste vide
            if (images == null || images.isEmpty()) {
                request.setImages(List.of());
            } else {
                // Sauvegarder les images et obtenir leurs URLs
                List<String> imageUrls = images.stream()
                    .map(this::saveImage)
                    .collect(Collectors.toList());
                
                // Ajouter les URLs des images à la requête
                request.setImages(imageUrls);
            }
            
            // Créer la maison
            HouseResponse response = service.create(request);
            return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Créé avec succès", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GlobalResponse<>(new Date(), true, "Erreur lors de la création: " + e.getMessage(), null));
        }
    }

    private String saveImage(MultipartFile file) {
        try {
            // Générer un nom de fichier unique
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            
            // Sauvegarder le fichier
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            
            // Retourner l'URL relative du fichier
            return "/images/" + fileName;
        } catch (IOException e) {
            throw new ApiException("Erreur lors de la sauvegarde de l'image: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Mettre à jour une maison", description = "Met à jour une maison existante par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Maison mise à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Maison non trouvée"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<HouseResponse>> update(
            @Parameter(description = "TrackingId de la maison à mettre à jour") @PathVariable UUID trackingId,
            @RequestBody HouseRequest request) {
        HouseResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Mis à jour avec succès", response));
    }

    @Operation(summary = "Rechercher une maison", description = "Trouve une maison par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Maison trouvée avec succès"),
        @ApiResponse(responseCode = "404", description = "Maison non trouvée"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<HouseResponse>> findById(
            @Parameter(description = "TrackingId de la maison à rechercher") @PathVariable UUID trackingId) {
        HouseResponse response = service.findById(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Trouvé avec succès", response));
    }

    @Operation(summary = "Lister toutes les maisons", description = "Récupère la liste de toutes les maisons")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des maisons récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<GlobalResponse<List<HouseResponse>>> findAll() {
        List<HouseResponse> responses = service.findAll();
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Récupéré tous avec succès", responses));
    }

    @Operation(summary = "Supprimer une maison", description = "Supprime une maison par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Maison supprimée avec succès"),
        @ApiResponse(responseCode = "404", description = "Maison non trouvée"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<Void>> delete(
            @Parameter(description = "TrackingId de la maison à supprimer") @PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Supprimé avec succès", null));
    }
}