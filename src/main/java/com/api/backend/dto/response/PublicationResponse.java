package com.api.backend.dto.response;

import com.api.backend.dto.response.CarResponse;
import com.api.backend.dto.response.HouseResponse;
import com.api.backend.dto.response.LandResponse;
import com.api.backend.dto.response.UserResponse;
import com.api.backend.enums.ItemType;
import com.api.backend.enums.PublicationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class PublicationResponse {
    private UUID trackingId;
    private UserResponse utilisateur;
    private String titre;
    private String contenu;
    private Long itemId;
    private ItemType typeItem;
    private PublicationStatus statut;
    private boolean enVedette;
    private CarResponse voiture;
    private HouseResponse maison;
    private LandResponse terrain;

    public PublicationResponse() {
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
    
    public String getTitre() {
        return titre;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public String getContenu() {
        return contenu;
    }
    
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
    
    public Long getItemid() {
        return itemId;
    }
    
    public void setItemid(Long itemId) {
        this.itemId = itemId;
    }
    
    public ItemType getTypeitem() {
        return typeItem;
    }
    
    public void setTypeitem(ItemType typeItem) {
        this.typeItem = typeItem;
    }
    
    public PublicationStatus getStatut() {
        return statut;
    }
    
    public void setStatut(PublicationStatus statut) {
        this.statut = statut;
    }
    
    public boolean getEnvedette() {
        return enVedette;
    }
    
    public void setEnvedette(boolean enVedette) {
        this.enVedette = enVedette;
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
    
}