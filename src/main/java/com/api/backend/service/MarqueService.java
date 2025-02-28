package com.api.backend.service;

import com.api.backend.dto.request.MarqueRequest;
import com.api.backend.dto.response.MarqueResponse;
import java.util.List;
import java.util.UUID;

public interface MarqueService {
    MarqueResponse create(MarqueRequest request);
    MarqueResponse update(UUID trackingId, MarqueRequest request);
    MarqueResponse findById(UUID trackingId);
    List<MarqueResponse> findAll();
    void delete(UUID trackingId);
    MarqueResponse findByNom(String nom);
}