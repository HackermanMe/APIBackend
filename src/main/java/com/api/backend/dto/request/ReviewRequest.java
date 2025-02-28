package com.api.backend.dto.request;

import com.api.backend.entity.User;
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

public class ReviewRequest {
    private UUID utilisateurId;
    private Integer note;
    private String commentaire;
    private Long idItem;
    private String typeItem;
    private UUID moderateurId;

    public ReviewRequest() {
    }

    public UUID getUtilisateurId() {
        return utilisateurId;
    }
    
    public void setUtilisateurId(UUID utilisateurId) {
        this.utilisateurId = utilisateurId;
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
    
    public UUID getModerateurId() {
        return moderateurId;
    }
    
    public void setModerateurId(UUID moderateurId) {
        this.moderateurId = moderateurId;
    }
    
}