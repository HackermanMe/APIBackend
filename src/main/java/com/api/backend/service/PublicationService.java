package com.api.backend.service;

import com.api.backend.dto.request.PublicationRequest;
import com.api.backend.dto.response.PublicationResponse;
import java.util.List;
import java.util.UUID;

public interface PublicationService {
    PublicationResponse create(PublicationRequest request);
    PublicationResponse update(UUID trackingId, PublicationRequest request);
    PublicationResponse findById(UUID trackingId);
    List<PublicationResponse> findAll();
    void delete(UUID trackingId);
}