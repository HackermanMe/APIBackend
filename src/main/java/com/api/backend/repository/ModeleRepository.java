package com.api.backend.repository;

import com.api.backend.entity.Modele;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;
import java.util.Optional;
import java.util.List;

public interface ModeleRepository extends JpaRepository<Modele, Long> {
    @Query("SELECT e FROM Modele e WHERE e.trackingId = :trackingId")
    Optional<Modele> findByTrackingId(@Param("trackingId") UUID trackingId);
    
    @Query("SELECT e FROM Modele e ORDER BY e.id")
    List<Modele> findAllOrdered();

    @Query("SELECT e FROM Modele e WHERE e.nom = :nom AND e.marque.trackingId = :marqueId")
    Optional<Modele> findByNomAndMarqueId(@Param("nom") String nom, @Param("marqueId") UUID marqueId);

    @Query("SELECT e FROM Modele e WHERE e.nom = :nom")
    Optional<Modele> findByNom(@Param("nom") String nom);
}