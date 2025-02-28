package com.api.backend.dto.response;

import com.api.backend.enums.AppointmentStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class AppointmentResponse {
    private UUID trackingId;
    private UserResponse client;
    private UserResponse agent;
    private LocalDateTime dateTime;
    private AppointmentStatus status;
    private String notes;
    private Long itemId;
    private String itemType;

    public AppointmentResponse() {
    }

    public UUID getTrackingid() {
        return trackingId;
    }
    
    public void setTrackingid(UUID trackingId) {
        this.trackingId = trackingId;
    }
    
    public UserResponse getClient() {
        return client;
    }
    
    public void setClient(UserResponse client) {
        this.client = client;
    }
    
    public UserResponse getAgent() {
        return agent;
    }
    
    public void setAgent(UserResponse agent) {
        this.agent = agent;
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