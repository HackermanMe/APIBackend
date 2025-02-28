package com.api.backend.enums;

public enum PublicationStatus {
    BROUILLON,    // Pour les annonces non publiées
    PUBLIE,       // Pour les annonces visibles
    ARCHIVE,      // Pour les annonces archivées
    EN_REVUE,     // Pour les annonces en cours de modération
    REJETE        // Pour les annonces rejetées par la modération
} 