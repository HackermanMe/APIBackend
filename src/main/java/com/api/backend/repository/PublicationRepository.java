package com.api.backend.repository;

import com.api.backend.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;
import java.util.Optional;
import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    @Query("SELECT e FROM Publication e WHERE e.trackingId = :trackingId")
    Optional<Publication> findByTrackingId(@Param("trackingId") UUID trackingId);
    
    @Query("SELECT e FROM Publication e ORDER BY e.id")
    List<Publication> findAllOrdered();
}