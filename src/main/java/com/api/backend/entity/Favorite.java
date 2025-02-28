package com.api.backend.entity;

import java.util.UUID;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import com.api.backend.enums.ItemType;
import com.api.backend.utils.AuditTable;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "favoris")
public class Favorite extends AuditTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID trackingId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User utilisateur;

    // Lien polymorphique vers l'item (voiture, maison ou terrain)
    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type", nullable = false)
    private ItemType itemType; // CAR, HOUSE, LAND

    @Column(nullable = false)
    private LocalDateTime dateAjout = LocalDateTime.now();

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

    private String note; // Note personnelle de l'utilisateur sur le favori

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

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public LocalDateTime getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(LocalDateTime dateAjout) {
		this.dateAjout = dateAjout;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
    
    

} 