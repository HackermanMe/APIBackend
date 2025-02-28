package com.api.backend.service;

import com.api.backend.dto.request.ReviewRequest;
import com.api.backend.dto.response.ReviewResponse;
import java.util.List;
import java.util.UUID;

public interface ReviewService {
    ReviewResponse create(ReviewRequest request);
    ReviewResponse update(UUID trackingId, ReviewRequest request);
    ReviewResponse findById(UUID trackingId);
    List<ReviewResponse> findAll();
    void delete(UUID trackingId);
}