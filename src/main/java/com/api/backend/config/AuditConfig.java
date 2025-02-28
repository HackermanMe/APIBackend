package com.api.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig {
    
    @Bean
    public AuditorAware<String> auditorProvider() {
        // Pour l'instant, on retourne un utilisateur par défaut
        return () -> java.util.Optional.of("SYSTEM");
        // Plus tard, vous pourrez le remplacer par l'utilisateur connecté 
        //return () -> java.util.Optional.of(getCurrentUser());
    }

    private String getCurrentUser() {
        // Implémentez la logique pour obtenir l'utilisateur connecté
        return "USER_CONNECTE"; // Remplacez par la logique réelle
    }
} 