package com.api.backend.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

import com.api.backend.enums.FuelType;
import com.api.backend.enums.TransmissionType;

@Entity
@Table(name = "cars")
public class Car extends BaseItem implements Serializable {
    
	@ManyToOne
	@JoinColumn(name = "modele_id")
	private Modele modele;

    @ManyToOne
    @JoinColumn(name = "proprietaire_id")
    private User proprietaire;

    @Column(nullable = false)
    private Integer annee;

    @Column(nullable = false)
    private Integer kilometrage;

    private String couleur;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType typeCarburant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransmissionType transmission;

    private Integer puissance;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "car_caracteristiques",
        joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id")
    )
    @Column(name = "caracteristique")
    private List<String> caracteristiques = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal prix;

    private boolean echangePossible;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "car_images",
        joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id")
    )
    @Column(name = "image_url")
    private List<String> images = new ArrayList<>();

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "note_moyenne")
    private Double noteMoyenne;

    @Column(name = "nombre_avis")
    private Integer nombreAvis;



	public Modele getModele() {
		return modele;
	}

	public void setModele(Modele modele) {
		this.modele = modele;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public Integer getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(Integer kilometrage) {
		this.kilometrage = kilometrage;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public FuelType getTypeCarburant() {
		return typeCarburant;
	}

	public void setTypeCarburant(FuelType typeCarburant) {
		this.typeCarburant = typeCarburant;
	}

	public TransmissionType getTransmission() {
		return transmission;
	}

	public void setTransmission(TransmissionType transmission) {
		this.transmission = transmission;
	}

	public Integer getPuissance() {
		return puissance;
	}

	public void setPuissance(Integer puissance) {
		this.puissance = puissance;
	}

	public List<String> getCaracteristiques() {
		return caracteristiques;
	}

	public void setCaracteristiques(List<String> caracteristiques) {
		this.caracteristiques = caracteristiques;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public boolean isEchangePossible() {
		return echangePossible;
	}

	public void setEchangePossible(boolean echangePossible) {
		this.echangePossible = echangePossible;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

    public User getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(User proprietaire) {
        this.proprietaire = proprietaire;
    }
	
}
