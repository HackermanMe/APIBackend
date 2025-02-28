package com.api.backend.controller;

import com.api.backend.dto.request.CarRequest;
import com.api.backend.dto.response.CarResponse;
import com.api.backend.service.CarService;
import com.api.backend.utils.GlobalResponse;
import com.api.backend.utils.exception.ApiException;
import org.springframework.transaction.annotation.Transactional;

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
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/cars")
@Tag(name = "Voitures", description = "API de gestion des voitures")
@Transactional
public class CarController {

    private static final String UPLOAD_DIR = "src/main/resources/static/images";
    private static final String IMAGE_BASE_URL = "/images";

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
        // Créer le répertoire d'upload s'il n'existe pas
        try {
            Files.createDirectories(Paths.get(UPLOAD_DIR));
        } catch (IOException e) {
            throw new RuntimeException("Impossible de créer le répertoire d'upload", e);
        }
    }

    @Operation(summary = "Créer une voiture", description = "Crée une nouvelle voiture dans le système")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voiture créée avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<GlobalResponse<CarResponse>> create(
        @RequestParam(value = "data") String carData,
        @RequestParam(value = "images", required = false) List<MultipartFile> images) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CarRequest request = mapper.readValue(carData, CarRequest.class);
            
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
            
            // Créer la voiture
            CarResponse response = service.create(request);
            return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Créé avec succès", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GlobalResponse<>(new Date(), true, "Erreur lors de la création: " + e.getMessage(), null));
        }
    }

    @Operation(summary = "Mettre à jour une voiture", description = "Met à jour une voiture existante par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voiture mise à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Voiture non trouvée"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<CarResponse>> update(
            @Parameter(description = "TrackingId de la voiture à mettre à jour") @PathVariable UUID trackingId,
            @RequestBody CarRequest request) {
        CarResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Mis à jour avec succès", response));
    }

    @Operation(summary = "Rechercher une voiture", description = "Trouve une voiture par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voiture trouvée avec succès"),
        @ApiResponse(responseCode = "404", description = "Voiture non trouvée"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<CarResponse>> findById(
            @Parameter(description = "TrackingId de la voiture à rechercher") @PathVariable UUID trackingId) {
        CarResponse response = service.findById(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Trouvé avec succès", response));
    }

    @Operation(summary = "Lister toutes les voitures", description = "Récupère la liste de toutes les voitures")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des voitures récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<GlobalResponse<List<CarResponse>>> findAll() {
        List<CarResponse> responses = service.findAll();
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Récupéré tous avec succès", responses));
    }

    @Operation(summary = "Supprimer une voiture", description = "Supprime une voiture par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Voiture supprimée avec succès"),
        @ApiResponse(responseCode = "404", description = "Voiture non trouvée"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<Void>> delete(
            @Parameter(description = "TrackingId de la voiture à supprimer") @PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Supprimé avec succès", null));
    }

    // @Operation(summary = "Créer une voiture avec des fichiers", description = "Crée une nouvelle voiture avec des images dans le système")
    // @ApiResponses(value = {
    //     @ApiResponse(responseCode = "200", description = "Voiture créée avec succès"),
    //     @ApiResponse(responseCode = "400", description = "Requête invalide"),
    //     @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    // })
    // @PostMapping("/with-files")
    // public ResponseEntity<GlobalResponse<CarResponse>> createWithFiles(
    //         @RequestParam("data") String carData,
    //         @RequestParam("images") List<MultipartFile> images) {
    //     try {
    //         ObjectMapper mapper = new ObjectMapper();
    //         CarRequest request = mapper.readValue(carData, CarRequest.class);
            
    //         // Sauvegarder les images et obtenir leurs URLs
    //         List<String> imageUrls = images.stream()
    //             .map(this::saveImage)
    //             .collect(Collectors.toList());
            
    //         // Ajouter les URLs des images à la requête
    //         request.setImages(imageUrls);
            
    //         // Créer la voiture
    //         CarResponse response = service.create(request);
    //         return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Créé avec succès", response));
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //             .body(new GlobalResponse<>(new Date(), true, "Erreur lors de la création: " + e.getMessage(), null));
    //     }
    // }

    private String saveImage(MultipartFile file) {
        try {
            // Générer un nom de fichier unique
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            
            // Sauvegarder le fichier
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            
            // Retourner l'URL relative du fichier
            return IMAGE_BASE_URL + "/" + fileName;
        } catch (IOException e) {
            throw new ApiException("Erreur lors de la sauvegarde de l'image: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}