package com.api.backend.dto.response;
import com.api.backend.enums.FuelType;
import com.api.backend.enums.TransmissionType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CarResponse {
    private UUID trackingId;
    private String titre;
    private ModeleResponse modele;
    private Integer annee;
    private Integer kilometrage;
    private String couleur;
    private FuelType typeCarburant;
    private TransmissionType transmission;
    private Integer puissance;
    private List<String> caracteristiques;
    private BigDecimal prix;
    private boolean negociable;
    private boolean paiementEnPlus;
    private boolean echangePossible;
    private String description;
    private List<String> images;
    private List<ReviewResponse> reviews;
    private Double noteMoyenne;
    private Integer nombreAvis;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public CarResponse() {
    }

    public UUID getTrackingid() {
        return trackingId;
    }
    
    public void setTrackingid(UUID trackingId) {
        this.trackingId = trackingId;
    }
    
    public ModeleResponse getModele() {
        return modele;
    }
    
    public void setModele(ModeleResponse modele) {
        this.modele = modele;
    }
    
    public Integer getAnnee() {
        return annee;
    }
    
    public void setAnnee(Integer annee) {
        this.annee = annee;
    }
    
    public Integer getKilometrage() {
        return kilometrage;
    }
    
    public void setKilometrage(Integer kilometrage) {
        this.kilometrage = kilometrage;
    }
    
    public String getCouleur() {
        return couleur;
    }
    
    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
    
    public FuelType getTypecarburant() {
        return typeCarburant;
    }
    
    public void setTypecarburant(FuelType typeCarburant) {
        this.typeCarburant = typeCarburant;
    }
    
    public TransmissionType getTransmission() {
        return transmission;
    }
    
    public void setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
    }
    
    public Integer getPuissance() {
        return puissance;
    }
    
    public void setPuissance(Integer puissance) {
        this.puissance = puissance;
    }
    
    public List<String> getCaracteristiques() {
        return caracteristiques;
    }
    
    public void setCaracteristiques(List<String> caracteristiques) {
        this.caracteristiques = caracteristiques;
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
    
    public boolean getEchangepossible() {
        return echangePossible;
    }
    
    public void setEchangepossible(boolean echangePossible) {
        this.echangePossible = echangePossible;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<String> getImages() {
        return images;
    }
    
    public void setImages(List<String> images) {
        this.images = images;
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