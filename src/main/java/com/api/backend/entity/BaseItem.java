package com.api.backend.entity;

import jakarta.persistence.*;
import com.api.backend.utils.AuditTable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseItem extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private UUID trackingId;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal prix;

    private boolean negociable;

    private boolean paiementEnPlus;

    @Column(name = "note_moyenne")
    private Double noteMoyenne;

    @Column(name = "nombre_avis")
    private Integer nombreAvis;

    @ElementCollection
    @CollectionTable(name = "caracteristiques_item")
    private List<String> caracteristiques;

    @ElementCollection
    @CollectionTable(name = "images_item")
    private List<String> images;


    @OneToMany
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<Review> reviews;

    public Long getId() {
        return id;
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

	public void setId(Long id) {
		this.id = id;
	}

    
} 