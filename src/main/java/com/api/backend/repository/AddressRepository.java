package com.api.backend.repository;

import com.api.backend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;
import java.util.Optional;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT e FROM Address e WHERE e.trackingId = :trackingId")
    Optional<Address> findByTrackingId(@Param("trackingId") UUID trackingId);
    
    @Query("SELECT e FROM Address e ORDER BY e.id")
    List<Address> findAllOrdered();
}