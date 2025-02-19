package com.fst.ArtSphere.services;


import com.fst.ArtSphere.entities.Produit;
import com.fst.ArtSphere.repositories.ArtisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fst.ArtSphere.entities.User;


import java.util.List;
import java.util.Optional;

@Service
public class ArtisteService {

    @Autowired
    private ArtisteRepository artisteRepository;

    


    
    public List<User> getAllArtistes() {
        return artisteRepository.findAll();
    }

    
    public Optional<User> getArtisteById(int  id) {
        return artisteRepository.findById(id);
    }

    
    public User updateArtiste(int id, User artisteDetails) {
        Optional<User> artiste = artisteRepository.findById(id);
        if (artiste.isPresent()) {
            User existingArtiste = artiste.get();
            existingArtiste.setNom(artisteDetails.getNom());
            existingArtiste.setPrenom(artisteDetails.getPrenom());
            existingArtiste.setAddress(artisteDetails.getAddress());
            existingArtiste.setEmail(artisteDetails.getEmail());
            existingArtiste.setPassword(artisteDetails.getPassword());
            existingArtiste.setBiographie(artisteDetails.getBiographie());
            return artisteRepository.save(existingArtiste);
        }
        return null;
    }

    
    public void deleteArtiste(int id) {
        artisteRepository.deleteById(id);
    }

    public List<Produit> getProduitsByArtisteId(int artisteId) {
        return artisteRepository.findProduitsByArtisteId(artisteId);
    }

    public Optional<User> login(String email, String password) {
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Email et mot de passe sont requis");
        }
        
        Optional<User> artiste = artisteRepository.findByEmail(email);
        
        if (artiste.isPresent() && artiste.get().getPassword().equals(password)) {
            return artiste;
        }
        
        return Optional.empty();
    }
} 