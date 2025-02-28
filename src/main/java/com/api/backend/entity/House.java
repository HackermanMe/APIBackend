package com.api.backend.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "houses")
public class House extends BaseItem implements Serializable {
    @Column(nullable = false)
    private String type; // Villa, Appartement, etc.

    @Column(nullable = false)
    private Integer chambres;

    @Column(nullable = false)
    private Integer sallesDeBain;

    @Column(nullable = false)
    private Double surface; // en m²

    @Column(nullable = false)
    private String adresse;

    private String ville;

    private String district;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal prix;

    private boolean negociable;

    private boolean paiementEnPlus;

    @ElementCollection
    @CollectionTable(name = "caracteristiques_maisons")
    private List<String> caracteristiques;

    @ElementCollection
    @CollectionTable(name = "images_maisons")
    private List<String> images;

    private boolean aGarage;
    
    private boolean aJardin;
    
    private boolean aPiscine;

    private Integer anneeConstruction;

    @ManyToOne
    @JoinColumn(name = "proprietaire_id")
    private User proprietaire;

    @Column(name = "note_moyenne")
    private Double noteMoyenne;

    @Column(name = "nombre_avis")
    private Integer nombreAvis;

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

    public boolean isaGarage() {
        return aGarage;
    }

    public void setaGarage(boolean aGarage) {
        this.aGarage = aGarage;
    }

    public boolean isaJardin() {
        return aJardin;
    }

    public void setaJardin(boolean aJardin) {
        this.aJardin = aJardin;
    }

    public boolean isaPiscine() {
        return aPiscine;
    }

    public void setaPiscine(boolean aPiscine) {
        this.aPiscine = aPiscine;
    }

    public Integer getAnneeConstruction() {
        return anneeConstruction;
    }

    public void setAnneeConstruction(Integer anneeConstruction) {
        this.anneeConstruction = anneeConstruction;
    }

    public User getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(User proprietaire) {
        this.proprietaire = proprietaire;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getChambres() {
        return chambres;
    }

    public void setChambres(Integer chambres) {
        this.chambres = chambres;
    }

    public Integer getSallesDeBain() {
        return sallesDeBain;
    }

    public void setSallesDeBain(Integer sallesDeBain) {
        this.sallesDeBain = sallesDeBain;
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
    
} 