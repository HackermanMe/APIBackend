package com.api.backend.repository;

import com.api.backend.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("SELECT t FROM Token t WHERE t.user.id = :userId AND t.expired = false AND t.revoked = false")
    List<Token> findAllValidTokenByUser(Long userId);

    Optional<Token> findByToken(String token);
} 