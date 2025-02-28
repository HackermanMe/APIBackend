package com.api.backend.dto.response;

import com.api.backend.dto.response.MarqueResponse;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ModeleResponse {
    private String nom;
    private MarqueResponse marque;
    private UUID trackingId;

    public ModeleResponse() {
    }

    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public MarqueResponse getMarque() {
        return marque;
    }
    
    public void setMarque(MarqueResponse marque) {
        this.marque = marque;
    }
    
    public UUID getTrackingid() {
        return trackingId;
    }
    
    public void setTrackingid(UUID trackingId) {
        this.trackingId = trackingId;
    }
    
}