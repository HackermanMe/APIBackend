package com.api.backend.service;

import com.api.backend.dto.request.HouseRequest;
import com.api.backend.dto.response.HouseResponse;
import java.util.List;
import java.util.UUID;

public interface HouseService {
    HouseResponse create(HouseRequest request);
    HouseResponse update(UUID trackingId, HouseRequest request);
    HouseResponse findById(UUID trackingId);
    List<HouseResponse> findAll();
    void delete(UUID trackingId);
}