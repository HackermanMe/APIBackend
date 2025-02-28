package com.api.backend.dto.response;

import com.api.backend.dto.response.CarResponse;
import com.api.backend.dto.response.HouseResponse;
import com.api.backend.dto.response.LandResponse;
import com.api.backend.dto.response.UserResponse;
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

public class FavoriteResponse {
    private UUID trackingId;
    private UserResponse utilisateur;
    private Long itemId;
    private ItemType itemType;
    private CarResponse voiture;
    private HouseResponse maison;
    private LandResponse terrain;
    private String note;

    public FavoriteResponse() {
    }

    public UUID getTrackingid() {
        return trackingId;
    }
    
    public void setTrackingid(UUID trackingId) {
        this.trackingId = trackingId;
    }
    
    public UserResponse getUtilisateur() {
        return utilisateur;
    }
    
    public void setUtilisateur(UserResponse utilisateur) {
        this.utilisateur = utilisateur;
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
    
    public CarResponse getVoiture() {
        return voiture;
    }
    
    public void setVoiture(CarResponse voiture) {
        this.voiture = voiture;
    }
    
    public HouseResponse getMaison() {
        return maison;
    }
    
    public void setMaison(HouseResponse maison) {
        this.maison = maison;
    }
    
    public LandResponse getTerrain() {
        return terrain;
    }
    
    public void setTerrain(LandResponse terrain) {
        this.terrain = terrain;
    }
    
    public String getNote() {
        return note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    
}