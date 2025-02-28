package com.api.backend.dto.response;

import com.api.backend.dto.response.AddressResponse;
import com.api.backend.dto.response.AppointmentResponse;
import com.api.backend.dto.response.CarResponse;
import com.api.backend.dto.response.FavoriteResponse;
import com.api.backend.dto.response.HouseResponse;
import com.api.backend.dto.response.LandResponse;
import com.api.backend.dto.response.PublicationResponse;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import com.api.backend.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private String id;
    private String email;
    private String role;
    private String prenom;
    private String nom;
    private String motDePasse;
    private String numeroDeTelephone;
    private AddressResponse adresse;
    private String imageDeProfil;
    private List<CarResponse> voitures;
    private List<HouseResponse> maisons;
    private List<LandResponse> terrains;
    private List<PublicationResponse> publications;
    private List<FavoriteResponse> favoris;
    private List<AppointmentResponse> rendezVousEnClient;
    private List<AppointmentResponse> rendezVousEnAgent;

    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
            .id(user.getTrackingId().toString())
            .email(user.getEmail())
            .role(user.getRole())
            .prenom(user.getPrenom())
            .nom(user.getNom())
            .build();
    }
}