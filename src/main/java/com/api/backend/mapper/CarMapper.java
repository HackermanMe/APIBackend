package com.api.backend.mapper;

import com.api.backend.dto.request.CarRequest;
import com.api.backend.dto.response.CarResponse;
import com.api.backend.dto.response.MarqueResponse;
import com.api.backend.dto.response.ModeleResponse;
import com.api.backend.entity.Car;
import com.api.backend.entity.Modele;
import com.api.backend.entity.Marque;
import com.api.backend.repository.ModeleRepository;
import com.api.backend.repository.ReviewRepository;
import com.api.backend.repository.MarqueRepository;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    private final ModeleRepository modeleRepository;
    private final MarqueRepository marqueRepository;

    public CarMapper(
            ModeleRepository modeleRepository, 
            ReviewRepository reviewsRepository, 
            MarqueRepository marqueRepository
            ) {
        this.modeleRepository = modeleRepository;
        this.marqueRepository = marqueRepository;
    }

    public Car toEntity(CarRequest request) {
        if (request == null) {
            return null;
        }
        
        Car entity = new Car();
        entity.setTrackingId(UUID.randomUUID());
        entity.setTitre(request.getTitre());
        
        // Gestion du modèle et de la marque
        if (request.getModeleId() != null) {
            // Si on a un modeleId, on essaie de le récupérer
            Modele modele = modeleRepository.findByTrackingId(request.getModeleId())
                .orElseGet(() -> {
                    // Si le modèle n'existe pas, on le crée
                    Modele newModele = new Modele();
                    newModele.setTrackingId(request.getModeleId());
                    newModele.setNom(request.getModeleNom());

                    // Gestion de la marque
                    if (request.getMarqueId() != null) {
                        Marque marque = marqueRepository.findByTrackingId(request.getMarqueId())
                            .orElseGet(() -> {
                                // Si la marque n'existe pas, on la crée
                                Marque newMarque = new Marque();
                                newMarque.setTrackingId(request.getMarqueId());
                                newMarque.setNom(request.getMarqueNom());
                                return marqueRepository.save(newMarque);
                            });
                        newModele.setMarque(marque);
                    }

                    return modeleRepository.save(newModele);
                });
            entity.setModele(modele);
        }
        
        entity.setAnnee(request.getAnnee());
        entity.setKilometrage(request.getKilometrage());
        entity.setCouleur(request.getCouleur());
        entity.setTypeCarburant(request.getTypecarburant());
        entity.setTransmission(request.getTransmission());
        entity.setPuissance(request.getPuissance());
        entity.setCaracteristiques(request.getCaracteristiques());
        entity.setPrix(request.getPrix());
        entity.setNegociable(request.getNegociable());
        entity.setPaiementEnPlus(request.getPaiementenplus());
        entity.setEchangePossible(request.getEchangepossible());
        entity.setDescription(request.getDescription());
        entity.setImages(request.getImages());
        
        entity.setNoteMoyenne(request.getNotemoyenne());
        entity.setNombreAvis(request.getNombreavis());
        return entity;
    }
    
    public CarResponse toResponse(Car entity) {
        if (entity == null) {
            return null;
        }
        
        CarResponse response = new CarResponse();
        response.setTrackingid(entity.getTrackingId());
        response.setTitre(entity.getTitre());
        if (entity.getModele() != null) {
            ModeleResponse r = new ModeleResponse();
            r.setTrackingid(entity.getModele().getTrackingId());
            r.setNom(entity.getModele().getNom());
            if (entity.getModele().getMarque() != null) {
                MarqueResponse marqueResponse = new MarqueResponse();
                marqueResponse.setTrackingid(entity.getModele().getMarque().getTrackingId());
                marqueResponse.setNom(entity.getModele().getMarque().getNom());
                r.setMarque(marqueResponse);
            }
            response.setModele(r);
        }
        response.setAnnee(entity.getAnnee());
        response.setKilometrage(entity.getKilometrage());
        response.setCouleur(entity.getCouleur());
        response.setTypecarburant(entity.getTypeCarburant());
        response.setTransmission(entity.getTransmission());
        response.setPuissance(entity.getPuissance());
        response.setCaracteristiques(entity.getCaracteristiques());
        response.setPrix(entity.getPrix());
        response.setNegociable(entity.isNegociable());
        response.setPaiementenplus(entity.isPaiementEnPlus());
        response.setEchangepossible(entity.isEchangePossible());
        response.setDescription(entity.getDescription());
        response.setImages(entity.getImages());
        
        response.setNotemoyenne(entity.getNoteMoyenne());
        response.setNombreavis(entity.getNombreAvis());
        return response;
    }
}