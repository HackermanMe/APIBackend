package com.api.backend.dto.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class LandRequest {
    private String titre;
    private Double surface;
    private String adresse;
    private String ville;
    private String district;
    private String description;
    private BigDecimal prix;
    private boolean negociable;
    private boolean paiementenplus;
    private boolean aEau;
    private boolean aElectricite;
    private boolean constructible;
    private List<String> caracteristiques;
    private List<String> images;
    private List<String> documentsCadastraux;
    private List<String> certificatUrbanisme;
    private UUID proprietaireId;
    private List<UUID> itemtagsIds;
    private List<UUID> reviewsIds;
    private Double notemoyenne;
    private Integer nombreavis;

    public LandRequest() {
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
    
    public boolean isPaiementenplus() {
        return paiementenplus;
    }
    
    public void setPaiementenplus(boolean paiementenplus) {
        this.paiementenplus = paiementenplus;
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
    
    public UUID getProprietaireId() {
        return proprietaireId;
    }
    
    public void setProprietaireId(UUID proprietaireId) {
        this.proprietaireId = proprietaireId;
    }
    
    public List<UUID> getItemtagsIds() {
        return itemtagsIds;
    }
    
    public void setItemtagsIds(List<UUID> itemtagsIds) {
        this.itemtagsIds = itemtagsIds;
    }
    
    public List<UUID> getReviewsIds() {
        return reviewsIds;
    }
    
    public void setReviewsIds(List<UUID> reviewsIds) {
        this.reviewsIds = reviewsIds;
    }
    
    public Double getNotemoyenne() {
        return notemoyenne;
    }
    
    public void setNotemoyenne(Double notemoyenne) {
        this.notemoyenne = notemoyenne;
    }
    
    public Integer getNombreavis() {
        return nombreavis;
    }
    
    public void setNombreavis(Integer nombreavis) {
        this.nombreavis = nombreavis;
    }
    
    public List<String> getDocumentsCadastraux() {
        return documentsCadastraux;
    }
    
    public void setDocumentsCadastraux(List<String> documentsCadastraux) {
        this.documentsCadastraux = documentsCadastraux;
    }
    
    public List<String> getCertificatUrbanisme() {
        return certificatUrbanisme;
    }
    
    public void setCertificatUrbanisme(List<String> certificatUrbanisme) {
        this.certificatUrbanisme = certificatUrbanisme;
    }
}