package com.api.backend.dto.request;

import com.api.backend.entity.Car;
import com.api.backend.entity.House;
import com.api.backend.entity.Land;
import com.api.backend.entity.User;
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

public class PublicationRequest {
    private UUID utilisateurId;
    private String titre;
    private String contenu;
    private Long itemId;
    private ItemType typeItem;
    private PublicationStatus statut;
    private boolean enVedette;
    private UUID voitureId;
    private UUID maisonId;
    private UUID terrainId;

    public PublicationRequest() {
    }

    public UUID getUtilisateurId() {
        return utilisateurId;
    }
    
    public void setUtilisateurId(UUID utilisateurId) {
        this.utilisateurId = utilisateurId;
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
    
}