package com.api.backend.dto.request;

import com.api.backend.entity.Marque;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ModeleRequest {
    private String nom;
    private UUID marqueId;

    public ModeleRequest() {
    }

    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public UUID getMarqueId() {
        return marqueId;
    }
    
    public void setMarqueId(UUID marqueId) {
        this.marqueId = marqueId;
    }
    
}