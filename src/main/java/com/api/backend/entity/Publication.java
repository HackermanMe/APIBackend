package com.api.backend.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.api.backend.enums.ItemType;
import com.api.backend.enums.PublicationStatus;
import com.api.backend.utils.AuditTable;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "publications")
public class Publication extends AuditTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID trackingId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User utilisateur;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String contenu;

    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type", nullable = false)
    private ItemType typeItem;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PublicationStatus statut;

    private boolean enVedette;

    private Integer compteurDeVues = 0;

    private Integer compteurFavoris = 0;

    private Integer compteurAppels = 0;


    // Relations polymorphiques pour accéder directement aux items
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Where(clause = "item_type = 'VOITURE'")
    private Car voiture;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Where(clause = "item_type = 'MAISON'")
    private House maison;

    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)
    @Where(clause = "item_type = 'TERRAIN'")
    private Land terrain;

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

	public User getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(User utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public ItemType getTypeItem() {
		return typeItem;
	}

	public void setTypeItem(ItemType typeItem) {
		this.typeItem = typeItem;
	}

	public PublicationStatus getStatut() {
		return statut;
	}

	public void setStatut(PublicationStatus statut) {
		this.statut = statut;
	}

	public boolean isEnVedette() {
		return enVedette;
	}

	public void setEnVedette(boolean enVedette) {
		this.enVedette = enVedette;
	}

	public Integer getCompteurDeVues() {
		return compteurDeVues;
	}

	public void setCompteurDeVues(Integer compteurDeVues) {
		this.compteurDeVues = compteurDeVues;
	}

	public Integer getCompteurFavoris() {
		return compteurFavoris;
	}

	public void setCompteurFavoris(Integer compteurFavoris) {
		this.compteurFavoris = compteurFavoris;
	}

	public Integer getCompteurAppels() {
		return compteurAppels;
	}

	public void setCompteurAppels(Integer compteurAppels) {
		this.compteurAppels = compteurAppels;
	}

	public Car getVoiture() {
		return voiture;
	}

	public void setVoiture(Car voiture) {
		this.voiture = voiture;
	}

	public House getMaison() {
		return maison;
	}

	public void setMaison(House maison) {
		this.maison = maison;
	}

	public Land getTerrain() {
		return terrain;
	}

	public void setTerrain(Land terrain) {
		this.terrain = terrain;
	}

    
} 