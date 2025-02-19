package com.fst.ArtSphere.services;

import com.fst.ArtSphere.entities.Produit;
import com.fst.ArtSphere.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {
    
    @Autowired
    private ProduitRepository produitRepository;

    // Create
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    // Read (tous les produits)
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    // Read (un seul produit par ID)
    public Optional<Produit> getProduitById(Integer id) {
        return produitRepository.findById(id);
    }

    // Update
    public Produit updateProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    // Delete
    public void deleteProduit(Integer id) {
        produitRepository.deleteById(id);
}
}
