package com.api.backend.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.Collection;

import com.api.backend.utils.AuditTable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "utilisateurs")
public class User extends AuditTable implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID trackingId;
    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    private String numeroDeTelephone;

    @Column(nullable = false)
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id")
    private Address adresse;

    private String imageDeProfil;

    @OneToMany(mappedBy = "proprietaire")
    private List<Car> voitures;

    @OneToMany(mappedBy = "proprietaire")
    private List<House> maisons;

    @OneToMany(mappedBy = "proprietaire")
    private List<Land> terrains;

    @OneToMany(mappedBy = "utilisateur")
    private List<Publication> publications;

    @OneToMany(mappedBy = "utilisateur")
    private List<Favorite> favoris;

    @OneToMany(mappedBy = "client")
    private List<Appointment> rendezVousEnClient;

    @OneToMany(mappedBy = "agent")
    private List<Appointment> rendezVousEnAgent;

    private boolean actif = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return actif;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UUID getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(UUID trackingId) {
		this.trackingId = trackingId;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getNumeroDeTelephone() {
		return numeroDeTelephone;
	}

	public void setNumeroDeTelephone(String numeroDeTelephone) {
		this.numeroDeTelephone = numeroDeTelephone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Address getAdresse() {
		return adresse;
	}

	public void setAdresse(Address adresse) {
		this.adresse = adresse;
	}

	public String getImageDeProfil() {
		return imageDeProfil;
	}

	public void setImageDeProfil(String imageDeProfil) {
		this.imageDeProfil = imageDeProfil;
	}

	public List<Car> getVoitures() {
		return voitures;
	}

	public void setVoitures(List<Car> voitures) {
		this.voitures = voitures;
	}

	public List<House> getMaisons() {
		return maisons;
	}

	public void setMaisons(List<House> maisons) {
		this.maisons = maisons;
	}

	public List<Land> getTerrains() {
		return terrains;
	}

	public void setTerrains(List<Land> terrains) {
		this.terrains = terrains;
	}

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	public List<Favorite> getFavoris() {
		return favoris;
	}

	public void setFavoris(List<Favorite> favoris) {
		this.favoris = favoris;
	}

	public List<Appointment> getRendezVousEnClient() {
		return rendezVousEnClient;
	}

	public void setRendezVousEnClient(List<Appointment> rendezVousEnClient) {
		this.rendezVousEnClient = rendezVousEnClient;
	}

	public List<Appointment> getRendezVousEnAgent() {
		return rendezVousEnAgent;
	}

	public void setRendezVousEnAgent(List<Appointment> rendezVousEnAgent) {
		this.rendezVousEnAgent = rendezVousEnAgent;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}
    
    

} 