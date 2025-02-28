package com.api.backend.mapper;

import com.api.backend.dto.request.HouseRequest;
import com.api.backend.dto.response.HouseResponse;
import com.api.backend.dto.response.ReviewResponse;
import com.api.backend.dto.response.UserResponse;
import com.api.backend.entity.House;
import com.api.backend.entity.Review;
import com.api.backend.repository.UserRepository;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class HouseMapper {
    private final UserRepository proprietaireRepository;

    public HouseMapper(UserRepository proprietaireRepository) {
        this.proprietaireRepository = proprietaireRepository;
    }

    public House toEntity(HouseRequest request) {
        if (request == null) {
            return null;
        }
        
        House entity = new House();
        entity.setTrackingId(UUID.randomUUID());
        entity.setTitre(request.getTitre());
        entity.setType(request.getType());
        entity.setChambres(request.getChambres());
        entity.setSallesDeBain(request.getSallesdebain());
        entity.setSurface(request.getSurface());
        entity.setAdresse(request.getAdresse());
        entity.setVille(request.getVille());
        entity.setDistrict(request.getDistrict());
        entity.setDescription(request.getDescription());
        entity.setPrix(request.getPrix());
        entity.setNegociable(request.getNegociable());
        entity.setPaiementEnPlus(request.getPaiementenplus());
        entity.setCaracteristiques(request.getCaracteristiques());
        entity.setImages(request.getImages());
        entity.setaGarage(request.getAgarage());
        entity.setaJardin(request.getAjardin());
        entity.setaPiscine(request.getApiscine());
        entity.setAnneeConstruction(request.getAnneeconstruction());
        if (request.getProprietaireId() != null) {
            entity.setProprietaire(proprietaireRepository.findByTrackingId(request.getProprietaireId())
                .orElseThrow(() -> new RuntimeException("User non trouvé avec trackingId: " + request.getProprietaireId())));
        }
        entity.setNoteMoyenne(request.getNotemoyenne());
        entity.setNombreAvis(request.getNombreavis());
        return entity;
    }
    
    public HouseResponse toResponse(House entity) {
        if (entity == null) {
            return null;
        }
        
        HouseResponse response = new HouseResponse();
        response.setTrackingid(entity.getTrackingId());
        response.setTitre(entity.getTitre());
        response.setType(entity.getType());
        response.setChambres(entity.getChambres());
        response.setSallesdebain(entity.getSallesDeBain());
        response.setSurface(entity.getSurface());
        response.setAdresse(entity.getAdresse());
        response.setVille(entity.getVille());
        response.setDistrict(entity.getDistrict());
        response.setDescription(entity.getDescription());
        response.setPrix(entity.getPrix());
        response.setNegociable(entity.isNegociable());
        response.setPaiementenplus(entity.isPaiementEnPlus());
        response.setCaracteristiques(entity.getCaracteristiques());
        response.setImages(entity.getImages());
        response.setAgarage(entity.isaGarage());
        response.setAjardin(entity.isaJardin());
        response.setApiscine(entity.isaPiscine());
        response.setAnneeconstruction(entity.getAnneeConstruction());
        if (entity.getProprietaire() != null) {
            UserResponse r = UserResponse.fromUser(entity.getProprietaire());
            response.setProprietaire(r);
        }
        response.setNotemoyenne(entity.getNoteMoyenne());
        response.setNombreavis(entity.getNombreAvis());
        return response;
    }


    private ReviewResponse toResponse(Review review) {
        if (review == null) return null;
        ReviewResponse response = new ReviewResponse();
        response.setTrackingid(review.getTrackingId());
        return response;
    }
}