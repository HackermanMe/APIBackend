package com.api.backend.service;

import com.api.backend.dto.request.ModeleRequest;
import com.api.backend.dto.response.ModeleResponse;
import java.util.List;
import java.util.UUID;

public interface ModeleService {
    ModeleResponse create(ModeleRequest request);
    ModeleResponse update(UUID trackingId, ModeleRequest request);
    ModeleResponse findById(UUID trackingId);
    List<ModeleResponse> findAll();
    void delete(UUID trackingId);
    ModeleResponse findByNomAndMarqueId(String nom, UUID marqueId);
    ModeleResponse findByNom(String nom);
}