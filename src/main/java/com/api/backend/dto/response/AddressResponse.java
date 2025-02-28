package com.api.backend.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class AddressResponse {
    private UUID trackingId;
    private String rue;
    private String ville;
    private String district;
    private String pays;
    private String codePostal;
    private Double latitude;
    private Double longitude;

    public AddressResponse() {
    }

    public UUID getTrackingid() {
        return trackingId;
    }
    
    public void setTrackingid(UUID trackingId) {
        this.trackingId = trackingId;
    }
    
    public String getRue() {
        return rue;
    }
    
    public void setRue(String rue) {
        this.rue = rue;
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
    
    public String getPays() {
        return pays;
    }
    
    public void setPays(String pays) {
        this.pays = pays;
    }
    
    public String getCodepostal() {
        return codePostal;
    }
    
    public void setCodepostal(String codePostal) {
        this.codePostal = codePostal;
    }
    
    public Double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public Double getLongitude() {
        return longitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
}