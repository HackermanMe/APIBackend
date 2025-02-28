package com.api.backend.repository;

import com.api.backend.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    Optional<House> findByTrackingId(UUID trackingId);

    @Query("SELECT h FROM House h LEFT JOIN FETCH h.proprietaire ORDER BY h.createdAt DESC")
    List<House> findAllOrdered();

    @Query("SELECT DISTINCT h FROM House h " +
           "LEFT JOIN FETCH h.proprietaire " +
           "WHERE h.trackingId = :trackingId")
    Optional<House> findByTrackingIdWithProprietaire(@Param("trackingId") UUID trackingId);

    @Query("SELECT DISTINCT h FROM House h " +
           "LEFT JOIN FETCH h.caracteristiques " +
           "WHERE h.trackingId = :trackingId")
    Optional<House> findByTrackingIdWithCaracteristiques(@Param("trackingId") UUID trackingId);

    @Query("SELECT DISTINCT h FROM House h " +
           "LEFT JOIN FETCH h.images " +
           "WHERE h.trackingId = :trackingId")
    Optional<House> findByTrackingIdWithImages(@Param("trackingId") UUID trackingId);

    @Query("SELECT DISTINCT h FROM House h " +
           "LEFT JOIN FETCH h.reviews " +
           "WHERE h.trackingId = :trackingId")
    Optional<House> findByTrackingIdWithReviews(@Param("trackingId") UUID trackingId);
}