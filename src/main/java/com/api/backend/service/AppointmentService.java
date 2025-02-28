package com.api.backend.service;

import com.api.backend.dto.request.AppointmentRequest;
import com.api.backend.dto.response.AppointmentResponse;
import java.util.List;
import java.util.UUID;

public interface AppointmentService {
    AppointmentResponse create(AppointmentRequest request);
    AppointmentResponse update(UUID trackingId, AppointmentRequest request);
    AppointmentResponse findById(UUID trackingId);
    List<AppointmentResponse> findAll();
    void delete(UUID trackingId);
}