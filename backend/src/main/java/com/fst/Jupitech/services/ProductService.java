package com.fst.Jupitech.services;

import com.fst.Jupitech.dto.ProductDTO;
import com.fst.Jupitech.entities.Produit;
import com.fst.Jupitech.repositories.ProduitRepository;
import com.fst.Jupitech.exceptions.ProductNotFoundException;
import com.fst.Jupitech.exceptions.DataIntegrityException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private ModelMapper modelMapper;

    // Create
    public ProductDTO saveProduit(ProductDTO produitDTO) {
    
            // Map DTO to entity
            Produit produit = modelMapper.map(produitDTO, Produit.class);

            // Save entity
            Produit savedProduit = produitRepository.save(produit);

            // Map back to DTO and return
            return modelMapper.map(savedProduit, ProductDTO.class);
       
    }

    // Read (all products)
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public List<ProductDTO> getAllProduitsAsDTO() {
        return produitRepository.findAll()
            .stream()
            .map(produit -> modelMapper.map(produit, ProductDTO.class))
            .collect(Collectors.toList());
    }

    // Read (one product by ID)
    public ProductDTO getProduitById(Integer id) {
        Optional<Produit> produit = produitRepository.findById(id);
        if (produit.isPresent()) {
            return modelMapper.map(produit.get(), ProductDTO.class);
        } else {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
    }



    // Update using DTO
    public ProductDTO updateProduct(Integer id, ProductDTO produitDTO) {
        if (id == null || !produitRepository.existsById(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found for update");
        }
        try {
            Produit produit = modelMapper.map(produitDTO, Produit.class);
            produit.setId(id); // Ensure the ID is set
            Produit updatedProduit = produitRepository.save(produit);
            return modelMapper.map(updatedProduit, ProductDTO.class);
        } catch (Exception e) {
            throw new DataIntegrityException("Failed to update the product: " + e.getMessage());
        }
    }

 


    public ProductDTO deleteProduct(Integer id) {
        Produit produit = produitRepository.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found for deletion"));
        try {
            produitRepository.deleteById(id);
            return modelMapper.map(produit, ProductDTO.class);
        } catch (Exception e) {
            throw new DataIntegrityException("Failed to delete the product: " + e.getMessage());
        }
    }
}
