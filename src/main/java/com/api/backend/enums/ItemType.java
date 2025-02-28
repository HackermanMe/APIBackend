package com.api.backend.enums;

public enum ItemType {
    VOITURE,
    MAISON,
    TERRAIN;

    public String getDiscriminatorValue() {
        return this.name();
    }
} 