package com.fst.Jupitech.controllers;

import com.fst.Jupitech.dto.ProductDTO;
import com.fst.Jupitech.entities.Produit;
import com.fst.Jupitech.services.ProductService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/produits")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService produitService;

    // Créer un produit
    // @PreAuthorize("hasAuthority('CLIENT')")
    @PostMapping("/add")
    public ResponseEntity<ProductDTO> creerProduit(@Valid @RequestBody ProductDTO produit) {
        return ResponseEntity.ok(produitService.saveProduit(produit));
    }

    // Obtenir tous les produits (DTO)
    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProduits() {
        try {
            List<ProductDTO> produits = produitService.getAllProduitsAsDTO();
            if (produits.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(produits, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtenir un produit par ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduitById(@PathVariable Integer id) {
        return ResponseEntity.ok(produitService.getProduitById(id)); 
    }

    // Mettre à jour un produit
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduit(@PathVariable Integer id, @RequestBody ProductDTO produitDTO) {
        return ResponseEntity.ok(produitService.updateProduct(id, produitDTO));
    }

    // Supprimer un produit
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduit(@PathVariable Integer id) {
        return ResponseEntity.ok(produitService.deleteProduct(id));
    }

    // Ajoutez un endpoint de test
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("ProduitController is working!");
    }
}
