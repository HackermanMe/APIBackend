package com.api.backend.service;

import com.api.backend.dto.request.LandRequest;
import com.api.backend.dto.response.LandResponse;
import java.util.List;
import java.util.UUID;

public interface LandService {
    LandResponse create(LandRequest request);
    LandResponse update(UUID trackingId, LandRequest request);
    LandResponse findById(UUID trackingId);
    List<LandResponse> findAll();
    void delete(UUID trackingId);
    LandResponse publish(UUID trackingId);
    LandResponse archive(UUID trackingId);
}