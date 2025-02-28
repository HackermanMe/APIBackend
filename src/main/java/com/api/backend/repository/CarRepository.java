package com.api.backend.repository;

import com.api.backend.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;
import java.util.Optional;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT DISTINCT c FROM Car c " +
           "LEFT JOIN FETCH c.modele m " +
           "LEFT JOIN FETCH m.marque " +
           "WHERE c.trackingId = :trackingId")
    Optional<Car> findByTrackingId(@Param("trackingId") UUID trackingId);
    
    @Query("SELECT DISTINCT c FROM Car c " +
           "LEFT JOIN FETCH c.modele m " +
           "LEFT JOIN FETCH m.marque " +
           "ORDER BY c.id")
    List<Car> findAllOrdered();

    @Query("SELECT DISTINCT c FROM Car c " +
           "LEFT JOIN FETCH c.caracteristiques " +
           "WHERE c.id = :id")
    Optional<Car> findByIdWithCaracteristiques(@Param("id") Long id);

    @Query("SELECT DISTINCT c FROM Car c " +
           "LEFT JOIN FETCH c.images " +
           "WHERE c.id = :id")
    Optional<Car> findByIdWithImages(@Param("id") Long id);

    @Query("SELECT DISTINCT c FROM Car c " +
           "LEFT JOIN FETCH c.reviews " +
           "WHERE c.id = :id")
    Optional<Car> findByIdWithReviews(@Param("id") Long id);

    @Query("SELECT DISTINCT c FROM Car c " +
           "LEFT JOIN FETCH c.modele m " +
           "LEFT JOIN FETCH m.marque " +
           "WHERE c.trackingId = :trackingId")
    Optional<Car> findByTrackingIdWithModele(@Param("trackingId") UUID trackingId);

    @Query("SELECT DISTINCT c FROM Car c " +
           "LEFT JOIN FETCH c.caracteristiques " +
           "WHERE c.trackingId = :trackingId")
    Optional<Car> findByTrackingIdWithCaracteristiques(@Param("trackingId") UUID trackingId);

    @Query("SELECT DISTINCT c FROM Car c " +
           "LEFT JOIN FETCH c.images " +
           "WHERE c.trackingId = :trackingId")
    Optional<Car> findByTrackingIdWithImages(@Param("trackingId") UUID trackingId);

    @Query("SELECT DISTINCT c FROM Car c " +
           "LEFT JOIN FETCH c.reviews " +
           "WHERE c.trackingId = :trackingId")
    Optional<Car> findByTrackingIdWithReviews(@Param("trackingId") UUID trackingId);
}