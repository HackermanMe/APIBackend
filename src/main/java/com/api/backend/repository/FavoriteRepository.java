package com.api.backend.repository;

import com.api.backend.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;
import java.util.Optional;
import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query("SELECT e FROM Favorite e WHERE e.trackingId = :trackingId")
    Optional<Favorite> findByTrackingId(@Param("trackingId") UUID trackingId);
    
    @Query("SELECT e FROM Favorite e ORDER BY e.id")
    List<Favorite> findAllOrdered();
}