package com.api.backend.service;

import com.api.backend.dto.request.CarRequest;
import com.api.backend.dto.response.CarResponse;
import java.util.List;
import java.util.UUID;

public interface CarService {
    CarResponse create(CarRequest request);
    CarResponse update(UUID trackingId, CarRequest request);
    CarResponse findById(UUID trackingId);
    List<CarResponse> findAll();
    void delete(UUID trackingId);
}