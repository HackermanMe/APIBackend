package com.api.backend.service;

import com.api.backend.dto.request.FavoriteRequest;
import com.api.backend.dto.response.FavoriteResponse;
import java.util.List;
import java.util.UUID;

public interface FavoriteService {
    FavoriteResponse create(FavoriteRequest request);
    FavoriteResponse update(UUID trackingId, FavoriteRequest request);
    FavoriteResponse findById(UUID trackingId);
    List<FavoriteResponse> findAll();
    void delete(UUID trackingId);
}