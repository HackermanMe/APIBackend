package com.api.backend.dto.request;

import com.api.backend.entity.Car;
import com.api.backend.entity.House;
import com.api.backend.entity.Land;
import com.api.backend.entity.User;
import com.api.backend.enums.ItemType;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class FavoriteRequest {
    private UUID utilisateurId;
    private Long itemId;
    private ItemType itemType;
    private UUID voitureId;
    private UUID maisonId;
    private UUID terrainId;
    private String note;

    public FavoriteRequest() {
    }

    public UUID getUtilisateurId() {
        return utilisateurId;
    }
    
    public void setUtilisateurId(UUID utilisateurId) {
        this.utilisateurId = utilisateurId;
    }
    
    public Long getItemid() {
        return itemId;
    }
    
    public void setItemid(Long itemId) {
        this.itemId = itemId;
    }
    
    public ItemType getItemtype() {
        return itemType;
    }
    
    public void setItemtype(ItemType itemType) {
        this.itemType = itemType;
    }
    
    public UUID getVoitureId() {
        return voitureId;
    }
    
    public void setVoitureId(UUID voitureId) {
        this.voitureId = voitureId;
    }
    
    public UUID getMaisonId() {
        return maisonId;
    }
    
    public void setMaisonId(UUID maisonId) {
        this.maisonId = maisonId;
    }
    
    public UUID getTerrainId() {
        return terrainId;
    }
    
    public void setTerrainId(UUID terrainId) {
        this.terrainId = terrainId;
    }
    
    public String getNote() {
        return note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    
}