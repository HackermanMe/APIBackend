package com.api.backend.mapper;

import com.api.backend.dto.request.AddressRequest;
import com.api.backend.dto.response.AddressResponse;
import com.api.backend.entity.Address;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toEntity(AddressRequest request) {
        if (request == null) {
            return null;
        }
        
        Address entity = new Address();
        entity.setTrackingId(UUID.randomUUID());
        entity.setRue(request.getRue());
        entity.setVille(request.getVille());
        entity.setDistrict(request.getDistrict());
        entity.setPays(request.getPays());
        entity.setCodePostal(request.getCodepostal());
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());
        return entity;
    }
    
    public AddressResponse toResponse(Address entity) {
        if (entity == null) {
            return null;
        }
        
        AddressResponse response = new AddressResponse();
        response.setTrackingid(entity.getTrackingId());
        response.setRue(entity.getRue());
        response.setVille(entity.getVille());
        response.setDistrict(entity.getDistrict());
        response.setPays(entity.getPays());
        response.setCodepostal(entity.getCodePostal());
        response.setLatitude(entity.getLatitude());
        response.setLongitude(entity.getLongitude());
        return response;
    }
}