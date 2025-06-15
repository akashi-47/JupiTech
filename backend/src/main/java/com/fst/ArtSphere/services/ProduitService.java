package com.fst.ArtSphere.services;

import com.fst.ArtSphere.entities.Produit;
import com.fst.ArtSphere.repositories.ProduitRepository;
import com.fst.ArtSphere.exceptions.ProductNotFoundException;
import com.fst.ArtSphere.exceptions.DataIntegrityException;
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
        try {
            return produitRepository.save(produit);
        } catch (Exception e) {
            throw new DataIntegrityException("Failed to save the product: " + e.getMessage());
        }
    }

    // Read (all products)
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    // Read (one product by ID)
    public Produit getProduitById(Integer id) {
        Optional<Produit> produit = produitRepository.findById(id);
        if (produit.isPresent()) {
            return produit.get();
        } else {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
    }

    // Update
    public Produit updateProduit(Produit produit) {
        if (produit.getId() == 0 || !produitRepository.existsById(produit.getId())) {
            throw new ProductNotFoundException("Product with ID " + produit.getId() + " not found for update");
        }
        try {
            return produitRepository.save(produit);
        } catch (Exception e) {
            throw new DataIntegrityException("Failed to update the product: " + e.getMessage());
        }
    }

    // Delete
    public void deleteProduit(Integer id) {
        if (!produitRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found for deletion");
        }
        try {
            produitRepository.deleteById(id);
        } catch (Exception e) {
            throw new DataIntegrityException("Failed to delete the product: " + e.getMessage());
        }
    }
}
