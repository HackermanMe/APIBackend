package com.api.backend.service;

import com.api.backend.dto.request.UserRequest;
import com.api.backend.dto.response.UserResponse;
import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse create(UserRequest request);
    UserResponse update(UUID trackingId, UserRequest request);
    UserResponse findById(UUID trackingId);
    List<UserResponse> findAll();
    void delete(UUID trackingId);
}