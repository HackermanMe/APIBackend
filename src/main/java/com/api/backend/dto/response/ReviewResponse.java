package com.api.backend.dto.response;

import com.api.backend.dto.response.UserResponse;
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

public class ReviewResponse {
    private UUID trackingId;
    private UserResponse utilisateur;
    private Integer note;
    private String commentaire;
    private Long idItem;
    private String typeItem;
    private UserResponse moderateur;

    public ReviewResponse() {
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
    
    public Integer getNote() {
        return note;
    }
    
    public void setNote(Integer note) {
        this.note = note;
    }
    
    public String getCommentaire() {
        return commentaire;
    }
    
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    public Long getIditem() {
        return idItem;
    }
    
    public void setIditem(Long idItem) {
        this.idItem = idItem;
    }
    
    public String getTypeitem() {
        return typeItem;
    }
    
    public void setTypeitem(String typeItem) {
        this.typeItem = typeItem;
    }
    
    public UserResponse getModerateur() {
        return moderateur;
    }
    
    public void setModerateur(UserResponse moderateur) {
        this.moderateur = moderateur;
    }
    
}