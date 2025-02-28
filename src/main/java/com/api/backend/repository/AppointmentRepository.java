package com.api.backend.repository;

import com.api.backend.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByTrackingId(UUID trackingId);
    
    boolean existsByAgentIdAndDateTime(Long agentId, LocalDateTime dateTime);
    
    @Query("SELECT a FROM Appointment a ORDER BY a.id")
    List<Appointment> findAllOrdered();
}