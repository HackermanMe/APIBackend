package com.api.backend.service;

import com.api.backend.dto.request.AddressRequest;
import com.api.backend.dto.response.AddressResponse;
import java.util.List;
import java.util.UUID;

public interface AddressService {
    AddressResponse create(AddressRequest request);
    AddressResponse update(UUID trackingId, AddressRequest request);
    AddressResponse findById(UUID trackingId);
    List<AddressResponse> findAll();
    void delete(UUID trackingId);
}