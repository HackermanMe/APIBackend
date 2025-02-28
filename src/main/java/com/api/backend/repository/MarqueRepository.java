package com.api.backend.repository;

import com.api.backend.entity.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;
import java.util.Optional;
import java.util.List;

public interface MarqueRepository extends JpaRepository<Marque, Long> {
    @Query("SELECT e FROM Marque e WHERE e.trackingId = :trackingId")
    Optional<Marque> findByTrackingId(@Param("trackingId") UUID trackingId);
    
    @Query("SELECT e FROM Marque e ORDER BY e.id")
    List<Marque> findAllOrdered();

    @Query("SELECT e FROM Marque e WHERE e.nom = :nom")
    Optional<Marque> findByNom(@Param("nom") String nom);
}