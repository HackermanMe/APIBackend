package com.api.backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.api.backend.enums.PublicationStatus;
@Entity
@Table(name = "lands")
public class Land {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID trackingId;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private Double surface;

    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String ville;

    private String district;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal prix;

    private boolean negociable;

    @Column(name = "paiement_en_plus")
    private boolean paiementEnPlus;

    @Column(name = "a_eau")
    private boolean aEau;

    @Column(name = "a_electricite")
    private boolean aElectricite;

    private boolean constructible;

    @ElementCollection
    @CollectionTable(name = "land_caracteristiques", joinColumns = @JoinColumn(name = "land_id"))
    @Column(name = "caracteristique")
    private List<String> caracteristiques = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "land_images", joinColumns = @JoinColumn(name = "land_id"))
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "land_documents_cadastraux", joinColumns = @JoinColumn(name = "land_id"))
    @Column(name = "document_url")
    private List<String> documentsCadastraux = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "land_certificat_urbanisme", joinColumns = @JoinColumn(name = "land_id"))
    @Column(name = "document_url")
    private List<String> certificatUrbanisme = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proprietaire_id")
    private User proprietaire;


    @OneToMany(mappedBy = "land", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "note_moyenne")
    private Double noteMoyenne = 0.0;

    @Column(name = "nombre_avis")
    private Integer nombreAvis = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private static PublicationStatus status = PublicationStatus.BROUILLON;

    @PrePersist
    protected void onCreate() {
        this.trackingId = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters et Setters

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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public boolean isNegociable() {
        return negociable;
    }

    public void setNegociable(boolean negociable) {
        this.negociable = negociable;
    }

    public boolean isPaiementEnPlus() {
        return paiementEnPlus;
    }

    public void setPaiementEnPlus(boolean paiementEnPlus) {
        this.paiementEnPlus = paiementEnPlus;
    }

    public boolean isAEau() {
        return aEau;
    }

    public void setAEau(boolean aEau) {
        this.aEau = aEau;
    }

    public boolean isAElectricite() {
        return aElectricite;
    }

    public void setAElectricite(boolean aElectricite) {
        this.aElectricite = aElectricite;
    }

    public boolean isConstructible() {
        return constructible;
    }

    public void setConstructible(boolean constructible) {
        this.constructible = constructible;
    }

    public List<String> getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(List<String> caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getDocumentsCadastraux() {
        return documentsCadastraux;
    }

    public void setDocumentsCadastraux(List<String> documentsCadastraux) {
        this.documentsCadastraux = documentsCadastraux;
    }

    public List<String> getCertificatUrbanisme() {
        return certificatUrbanisme;
    }

    public void setCertificatUrbanisme(List<String> certificatUrbanisme) {
        this.certificatUrbanisme = certificatUrbanisme;
    }

    public User getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(User proprietaire) {
        this.proprietaire = proprietaire;
    }


    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Double getNoteMoyenne() {
        return noteMoyenne;
    }

    public void setNoteMoyenne(Double noteMoyenne) {
        this.noteMoyenne = noteMoyenne;
    }

    public Integer getNombreAvis() {
        return nombreAvis;
    }

    public void setNombreAvis(Integer nombreAvis) {
        this.nombreAvis = nombreAvis;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static PublicationStatus getStatus() {
        return status;
    }

    public static void setStatus(PublicationStatus status) {
        status = status;
    }
} 