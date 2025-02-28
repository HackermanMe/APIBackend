package com.api.backend.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class LandResponse {
    private UUID trackingId;
    private String titre;
    private Double surface;
    private String adresse;
    private String ville;
    private String district;
    private String description;
    private BigDecimal prix;
    private boolean negociable;
    private boolean paiementEnPlus;
    private boolean aEau;
    private boolean aElectricite;
    private boolean constructible;
    private List<String> caracteristiques;
    private List<String> images;
    private UserResponse proprietaire;
    private List<ReviewResponse> reviews;
    private Double noteMoyenne;
    private Integer nombreAvis;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LandResponse() {
    }

    public UUID getTrackingId() {
        return trackingId;
    }
    
    public void setTrackingId(UUID trackingId) {
        this.trackingId = trackingId;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public Double getSurface() {
        return surface;
    }
    
    public void setSurface(Double surface) {
        this.surface = surface;
    }
    
    public String getAdresse() {
        return adresse;
    }
    
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getVille() {
        return ville;
    }
    
    public void setVille(String ville) {
        this.ville = ville;
    }
    
    public String getDistrict() {
        return district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public BigDecimal getPrix() {
        return prix;
    }
    
    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }
    
    public boolean isNegociable() {
        return negociable;
    }
    
    public void setNegociable(boolean negociable) {
        this.negociable = negociable;
    }
    
    public boolean isPaiementEnPlus() {
        return paiementEnPlus;
    }
    
    public void setPaiementEnPlus(boolean paiementEnPlus) {
        this.paiementEnPlus = paiementEnPlus;
    }
    
    public boolean isAEau() {
        return aEau;
    }
    
    public void setAEau(boolean aEau) {
        this.aEau = aEau;
    }
    
    public boolean isAElectricite() {
        return aElectricite;
    }
    
    public void setAElectricite(boolean aElectricite) {
        this.aElectricite = aElectricite;
    }
    
    public boolean isConstructible() {
        return constructible;
    }
    
    public void setConstructible(boolean constructible) {
        this.constructible = constructible;
    }
    
    public List<String> getCaracteristiques() {
        return caracteristiques;
    }
    
    public void setCaracteristiques(List<String> caracteristiques) {
        this.caracteristiques = caracteristiques;
    }
    
    public List<String> getImages() {
        return images;
    }
    
    public void setImages(List<String> images) {
        this.images = images;
    }
    
    public UserResponse getProprietaire() {
        return proprietaire;
    }
    
    public void setProprietaire(UserResponse proprietaire) {
        this.proprietaire = proprietaire;
    }
    public List<ReviewResponse> getReviews() {
        return reviews;
    }
    
    public void setReviews(List<ReviewResponse> reviews) {
        this.reviews = reviews;
    }
    
    public Double getNoteMoyenne() {
        return noteMoyenne;
    }
    
    public void setNoteMoyenne(Double noteMoyenne) {
        this.noteMoyenne = noteMoyenne;
    }
    
    public Integer getNombreAvis() {
        return nombreAvis;
    }
    
    public void setNombreAvis(Integer nombreAvis) {
        this.nombreAvis = nombreAvis;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}