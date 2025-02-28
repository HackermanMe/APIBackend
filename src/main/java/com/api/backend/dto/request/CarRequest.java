package com.api.backend.dto.request;

import com.api.backend.enums.FuelType;
import com.api.backend.enums.TransmissionType;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CarRequest {
    private String titre;
    private UUID modeleId;
    private UUID marqueId;
    private String modeleNom;
    private String marqueNom;
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
    private List<UUID> itemTagsIds;
    private List<UUID> reviewsIds;
    private Double noteMoyenne;
    private Integer nombreAvis;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public CarRequest() {
    }

    public UUID getModeleId() {
        return modeleId;
    }
    
    public void setModeleId(UUID modeleId) {
        this.modeleId = modeleId;
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
    
    public List<UUID> getItemtagsIds() {
        return itemTagsIds;
    }
    
    public void setItemtagsIds(List<UUID> itemTagsIds) {
        this.itemTagsIds = itemTagsIds;
    }
    
    public List<UUID> getReviewsIds() {
        return reviewsIds;
    }
    
    public void setReviewsIds(List<UUID> reviewsIds) {
        this.reviewsIds = reviewsIds;
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
    
    public String getModeleNom() {
        return modeleNom;
    }

    public void setModeleNom(String modeleNom) {
        this.modeleNom = modeleNom;
    }

    public String getMarqueNom() {
        return marqueNom;
    }

    public void setMarqueNom(String marqueNom) {
        this.marqueNom = marqueNom;
    }

    public UUID getMarqueId() {
        return marqueId;
    }

    public void setMarqueId(UUID marqueId) {
        this.marqueId = marqueId;
    }
}