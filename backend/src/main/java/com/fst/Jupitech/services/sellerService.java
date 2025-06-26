package com.fst.Jupitech.services;


import com.fst.Jupitech.dto.ProductDTO;
import com.fst.Jupitech.entities.Produit;
import com.fst.Jupitech.repositories.SellerRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fst.Jupitech.entities.User;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> getProduitsBysellerId(int sellerId) {
        List<Produit> produits = sellerRepository.findProduitsBysellerId(sellerId);
        return produits.stream()
                .map(produit -> modelMapper.map(produit, ProductDTO.class))
                .toList();
  
   
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