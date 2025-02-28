package com.api.backend.dto.response;

import java.math.BigDecimal;
import java.util.List;

import java.util.UUID;

public class HouseResponse {
    private UUID trackingId;
    private String titre;
    private String type;
    private Integer chambres;
    private Integer sallesDeBain;
    private Double surface;
    private String adresse;
    private String ville;
    private String district;
    private String description;
    private BigDecimal prix;
    private boolean negociable;
    private boolean paiementEnPlus;
    private List<String> caracteristiques;
    private List<String> images;
    private boolean aGarage;
    private boolean aJardin;
    private boolean aPiscine;
    private Integer anneeConstruction;
    private UserResponse proprietaire;
    private List<ReviewResponse> reviews;
    private Double noteMoyenne;
    private Integer nombreAvis;

    public HouseResponse() {
    }

    public UUID getTrackingid() {
        return trackingId;
    }
    
    public void setTrackingid(UUID trackingId) {
        this.trackingId = trackingId;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Integer getChambres() {
        return chambres;
    }
    
    public void setChambres(Integer chambres) {
        this.chambres = chambres;
    }
    
    public Integer getSallesdebain() {
        return sallesDeBain;
    }
    
    public void setSallesdebain(Integer sallesDeBain) {
        this.sallesDeBain = sallesDeBain;
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
    
    public boolean getNegociable() {
        return negociable;
    }
    
    public void setNegociable(boolean negociable) {
        this.negociable = negociable;
    }
    
    public boolean getPaiementenplus() {
        return paiementEnPlus;
    }
    
    public void setPaiementenplus(boolean paiementEnPlus) {
        this.paiementEnPlus = paiementEnPlus;
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
    
    public boolean getAgarage() {
        return aGarage;
    }
    
    public void setAgarage(boolean aGarage) {
        this.aGarage = aGarage;
    }
    
    public boolean getAjardin() {
        return aJardin;
    }
    
    public void setAjardin(boolean aJardin) {
        this.aJardin = aJardin;
    }
    
    public boolean getApiscine() {
        return aPiscine;
    }
    
    public void setApiscine(boolean aPiscine) {
        this.aPiscine = aPiscine;
    }
    
    public Integer getAnneeconstruction() {
        return anneeConstruction;
    }
    
    public void setAnneeconstruction(Integer anneeConstruction) {
        this.anneeConstruction = anneeConstruction;
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
    
    public Double getNotemoyenne() {
        return noteMoyenne;
    }
    
    public void setNotemoyenne(Double noteMoyenne) {
        this.noteMoyenne = noteMoyenne;
    }
    
    public Integer getNombreavis() {
        return nombreAvis;
    }
    
    public void setNombreavis(Integer nombreAvis) {
        this.nombreAvis = nombreAvis;
    }
    
}