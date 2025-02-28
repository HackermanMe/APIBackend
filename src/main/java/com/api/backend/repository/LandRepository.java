package com.api.backend.repository;

import com.api.backend.entity.Land;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LandRepository extends JpaRepository<Land, Long> {
    Optional<Land> findByTrackingId(UUID trackingId);

    @Query("SELECT l FROM Land l LEFT JOIN FETCH l.proprietaire ORDER BY l.createdAt DESC")
    List<Land> findAllOrdered();

    @Query("SELECT DISTINCT l FROM Land l " +
           "LEFT JOIN FETCH l.proprietaire " +
           "WHERE l.trackingId = :trackingId")
    Optional<Land> findByTrackingIdWithProprietaire(@Param("trackingId") UUID trackingId);

    @Query("SELECT DISTINCT l FROM Land l " +
           "LEFT JOIN FETCH l.caracteristiques " +
           "WHERE l.trackingId = :trackingId")
    Optional<Land> findByTrackingIdWithCaracteristiques(@Param("trackingId") UUID trackingId);

    @Query("SELECT DISTINCT l FROM Land l " +
           "LEFT JOIN FETCH l.images " +
           "WHERE l.trackingId = :trackingId")
    Optional<Land> findByTrackingIdWithImages(@Param("trackingId") UUID trackingId);

    @Query("SELECT DISTINCT l FROM Land l " +
           "LEFT JOIN FETCH l.reviews " +
           "WHERE l.trackingId = :trackingId")
    Optional<Land> findByTrackingIdWithReviews(@Param("trackingId") UUID trackingId);
}