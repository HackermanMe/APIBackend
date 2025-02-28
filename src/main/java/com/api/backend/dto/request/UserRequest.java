package com.api.backend.dto.request;


import java.util.List;
import java.util.UUID;

public class UserRequest {
    private String prenom;
    private String nom;
    private String email;
    private String motDePasse;
    private String numeroDeTelephone;
    private String role;
    private UUID adresseId;
    private String imageDeProfil;
    private List<UUID> voituresIds;
    private List<UUID> maisonsIds;
    private List<UUID> terrainsIds;
    private List<UUID> publicationsIds;
    private List<UUID> favorisIds;
    private List<UUID> rendezVousEnClientIds;
    private List<UUID> rendezVousEnAgentIds;

    public UserRequest() {
    }

    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMotdepasse() {
        return motDePasse;
    }
    
    public void setMotdepasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    public String getNumerodetelephone() {
        return numeroDeTelephone;
    }
    
    public void setNumerodetelephone(String numeroDeTelephone) {
        this.numeroDeTelephone = numeroDeTelephone;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public UUID getAdresseId() {
        return adresseId;
    }
    
    public void setAdresseId(UUID adresseId) {
        this.adresseId = adresseId;
    }
    
    public String getImagedeprofil() {
        return imageDeProfil;
    }
    
    public void setImagedeprofil(String imageDeProfil) {
        this.imageDeProfil = imageDeProfil;
    }
    
    public List<UUID> getVoituresIds() {
        return voituresIds;
    }
    
    public void setVoituresIds(List<UUID> voituresIds) {
        this.voituresIds = voituresIds;
    }
    
    public List<UUID> getMaisonsIds() {
        return maisonsIds;
    }
    
    public void setMaisonsIds(List<UUID> maisonsIds) {
        this.maisonsIds = maisonsIds;
    }
    
    public List<UUID> getTerrainsIds() {
        return terrainsIds;
    }
    
    public void setTerrainsIds(List<UUID> terrainsIds) {
        this.terrainsIds = terrainsIds;
    }
    
    public List<UUID> getPublicationsIds() {
        return publicationsIds;
    }
    
    public void setPublicationsIds(List<UUID> publicationsIds) {
        this.publicationsIds = publicationsIds;
    }
    
    public List<UUID> getFavorisIds() {
        return favorisIds;
    }
    
    public void setFavorisIds(List<UUID> favorisIds) {
        this.favorisIds = favorisIds;
    }
    
    public List<UUID> getRendezvousenclientIds() {
        return rendezVousEnClientIds;
    }
    
    public void setRendezvousenclientIds(List<UUID> rendezVousEnClientIds) {
        this.rendezVousEnClientIds = rendezVousEnClientIds;
    }
    
    public List<UUID> getRendezvousenagentIds() {
        return rendezVousEnAgentIds;
    }
    
    public void setRendezvousenagentIds(List<UUID> rendezVousEnAgentIds) {
        this.rendezVousEnAgentIds = rendezVousEnAgentIds;
    }
    
}