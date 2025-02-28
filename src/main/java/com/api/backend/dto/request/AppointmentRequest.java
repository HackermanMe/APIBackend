package com.api.backend.dto.request;

import com.api.backend.entity.User;
import com.api.backend.enums.AppointmentStatus;

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

public class AppointmentRequest {
    private UUID clientId;
    private UUID agentId;
    private LocalDateTime dateTime;
    private AppointmentStatus status;
    private String notes;
    private Long itemId;
    private String itemType;

    public AppointmentRequest() {
    }

    public UUID getClientId() {
        return clientId;
    }
    
    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }
    
    public UUID getAgentId() {
        return agentId;
    }
    
    public void setAgentId(UUID agentId) {
        this.agentId = agentId;
    }
    
    public LocalDateTime getDatetime() {
        return dateTime;
    }
    
    public void setDatetime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    
    public AppointmentStatus getStatus() {
        return status;
    }
    
    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public Long getItemid() {
        return itemId;
    }
    
    public void setItemid(Long itemId) {
        this.itemId = itemId;
    }
    
    public String getItemtype() {
        return itemType;
    }
    
    public void setItemtype(String itemType) {
        this.itemType = itemType;
    }
    
}