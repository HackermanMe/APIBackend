package com.api.backend.controller;

import com.api.backend.dto.request.LandRequest;
import com.api.backend.dto.response.LandResponse;
import com.api.backend.service.LandService;
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
@RequestMapping("/api/v1/lands")
@Tag(name = "Terrains", description = "API de gestion des terrains")
@Transactional
public class LandController {

    private static final String STATIC_DIR = "src/main/resources/static";
    private static final String IMAGES_DIR = STATIC_DIR + "/images";
    private static final String DOCUMENTS_DIR = STATIC_DIR + "/documents";

    private final LandService service;

    public LandController(LandService service) {
        this.service = service;
        // Créer les répertoires d'upload s'ils n'existent pas
        try {
            Files.createDirectories(Paths.get(STATIC_DIR));
            Files.createDirectories(Paths.get(IMAGES_DIR));
            Files.createDirectories(Paths.get(DOCUMENTS_DIR));
        } catch (IOException e) {
            throw new RuntimeException("Impossible de créer les répertoires d'upload", e);
        }
    }

    @Operation(summary = "Créer un terrain", description = "Crée un nouveau terrain dans le système")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Terrain créé avec succès"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<GlobalResponse<LandResponse>> create(
            @RequestParam("data") String landData,
            @RequestParam(value = "images", required = false) List<MultipartFile> images,
            @RequestParam(value = "documentsCadastraux", required = false) List<MultipartFile> documentsCadastraux,
            @RequestParam(value = "certificatUrbanisme", required = false) List<MultipartFile> certificatUrbanisme) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            LandRequest request = mapper.readValue(landData, LandRequest.class);
            
            // Gérer les images
            if (images != null && !images.isEmpty() && !images.get(0).isEmpty()) {
                List<String> imageUrls = images.stream()
                    .map(this::saveImage)
                    .collect(Collectors.toList());
                request.setImages(imageUrls);
            } else {
                request.setImages(List.of());
            }
            
            // Gérer les documents cadastraux
            if (documentsCadastraux != null && !documentsCadastraux.isEmpty() && !documentsCadastraux.get(0).isEmpty()) {
                List<String> docUrls = documentsCadastraux.stream()
                    .map(this::saveDocument)
                    .collect(Collectors.toList());
                request.setDocumentsCadastraux(docUrls);
            } else {
                request.setDocumentsCadastraux(List.of());
            }
            
            // Gérer les certificats d'urbanisme
            if (certificatUrbanisme != null && !certificatUrbanisme.isEmpty() && !certificatUrbanisme.get(0).isEmpty()) {
                List<String> certUrls = certificatUrbanisme.stream()
                    .map(this::saveDocument)
                    .collect(Collectors.toList());
                request.setCertificatUrbanisme(certUrls);
            } else {
                request.setCertificatUrbanisme(List.of());
            }
            
            // Créer le terrain
            LandResponse response = service.create(request);
            return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Créé avec succès", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GlobalResponse<>(new Date(), true, "Erreur lors de la création: " + e.getMessage(), null));
        }
    }

    private String saveImage(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new ApiException("Le fichier est vide", HttpStatus.BAD_REQUEST);
            }
            
            // Générer un nom de fichier unique
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            
            // Sauvegarder le fichier
            Path filePath = Paths.get(IMAGES_DIR).resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            
            // Retourner l'URL relative du fichier
            return "/images/" + fileName;
        } catch (IOException e) {
            throw new ApiException("Erreur lors de la sauvegarde de l'image: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String saveDocument(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new ApiException("Le fichier est vide", HttpStatus.BAD_REQUEST);
            }
            
            // Générer un nom de fichier unique
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            
            // Sauvegarder le fichier
            Path filePath = Paths.get(DOCUMENTS_DIR).resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            
            // Retourner l'URL relative du fichier
            return "/documents/" + fileName;
        } catch (IOException e) {
            throw new ApiException("Erreur lors de la sauvegarde du document: " + e.getMessage(), 
                HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Mettre à jour un terrain", description = "Met à jour un terrain existant par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Terrain mis à jour avec succès"),
        @ApiResponse(responseCode = "404", description = "Terrain non trouvé"),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<LandResponse>> update(
            @Parameter(description = "TrackingId du terrain à mettre à jour") @PathVariable UUID trackingId,
            @RequestBody LandRequest request) {
        LandResponse response = service.update(trackingId, request);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Mis à jour avec succès", response));
    }

    @Operation(summary = "Rechercher un terrain", description = "Trouve un terrain par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Terrain trouvé avec succès"),
        @ApiResponse(responseCode = "404", description = "Terrain non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<LandResponse>> findById(
            @Parameter(description = "TrackingId du terrain à rechercher") @PathVariable UUID trackingId) {
        LandResponse response = service.findById(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Trouvé avec succès", response));
    }

    @Operation(summary = "Lister tous les terrains", description = "Récupère la liste de tous les terrains")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Liste des terrains récupérée avec succès"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping
    public ResponseEntity<GlobalResponse<List<LandResponse>>> findAll() {
        List<LandResponse> responses = service.findAll();
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Récupéré tous avec succès", responses));
    }

    @Operation(summary = "Supprimer un terrain", description = "Supprime un terrain par son trackingId")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Terrain supprimé avec succès"),
        @ApiResponse(responseCode = "404", description = "Terrain non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @DeleteMapping("/{trackingId}")
    public ResponseEntity<GlobalResponse<Void>> delete(
            @Parameter(description = "TrackingId du terrain à supprimer") @PathVariable UUID trackingId) {
        service.delete(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Supprimé avec succès", null));
    }

    @Operation(summary = "Publier une annonce", description = "Publie une annonce de terrain")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Annonce publiée avec succès"),
        @ApiResponse(responseCode = "404", description = "Terrain non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{trackingId}/publish")
    public ResponseEntity<GlobalResponse<LandResponse>> publish(
            @Parameter(description = "TrackingId du terrain à publier") @PathVariable UUID trackingId) {
        LandResponse response = service.publish(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Annonce publiée avec succès", response));
    }

    @Operation(summary = "Archiver une annonce", description = "Archive une annonce de terrain")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Annonce archivée avec succès"),
        @ApiResponse(responseCode = "404", description = "Terrain non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PutMapping("/{trackingId}/archive")
    public ResponseEntity<GlobalResponse<LandResponse>> archive(
            @Parameter(description = "TrackingId du terrain à archiver") @PathVariable UUID trackingId) {
        LandResponse response = service.archive(trackingId);
        return ResponseEntity.ok(new GlobalResponse<>(new Date(), false, "Annonce archivée avec succès", response));
    }
}