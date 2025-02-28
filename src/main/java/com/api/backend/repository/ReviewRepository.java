package com.api.backend.repository;

import com.api.backend.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;
import java.util.Optional;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT e FROM Review e WHERE e.trackingId = :trackingId")
    Optional<Review> findByTrackingId(@Param("trackingId") UUID trackingId);
    
    @Query("SELECT e FROM Review e ORDER BY e.id")
    List<Review> findAllOrdered();
}