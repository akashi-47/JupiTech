package com.fst.Jupitech.services;


import com.fst.Jupitech.entities.Produit;
import com.fst.Jupitech.repositories.sellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fst.Jupitech.entities.User;


import java.util.List;
import java.util.Optional;

@Service
public class sellerService {

    @Autowired
    private sellerRepository sellerRepository;

    


    
    public List<User> getAllsellers() {
        return sellerRepository.findAll();
    }

    
    public Optional<User> getsellerById(int  id) {
        return sellerRepository.findById(id);
    }

    
    public User updateseller(int id, User sellerDetails) {
        Optional<User> seller = sellerRepository.findById(id);
        if (seller.isPresent()) {
            User existingseller = seller.get();
            existingseller.setNom(sellerDetails.getNom());
            existingseller.setPrenom(sellerDetails.getPrenom());
            existingseller.setAddress(sellerDetails.getAddress());
            existingseller.setEmail(sellerDetails.getEmail());
            existingseller.setPassword(sellerDetails.getPassword());
            existingseller.setBusinessname(sellerDetails.getBusinessname());
            return sellerRepository.save(existingseller);
        }
        return null;
    }

    
    public void deleteseller(int id) {
        sellerRepository.deleteById(id);
    }

    public List<Produit> getProduitsBysellerId(int sellerId) {
        return sellerRepository.findProduitsBysellerId(sellerId);
    }

    public Optional<User> login(String email, String password) {
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Email et mot de passe sont requis");
        }
        
        Optional<User> seller = sellerRepository.findByEmail(email);
        
        if (seller.isPresent() && seller.get().getPassword().equals(password)) {
            return seller;
        }
        
        return Optional.empty();
    }
} 