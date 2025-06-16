package com.fst.Jupitech.controllers;

import com.fst.Jupitech.entities.Produit;
import com.fst.Jupitech.services.ProduitService;
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
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    // Créer un produit
@PreAuthorize("hasAuthority('CLIENT')")
   @PostMapping("/add")
    public ResponseEntity<Produit> creerProduit(@RequestBody Produit produit) {

        return ResponseEntity.ok(produitService.saveProduit(produit));
    }

    // Obtenir tous les produits
    @GetMapping("/all")
    public ResponseEntity<?> getAllProduits() {
        try {
            List<Produit> produits = produitService.getAllProduits();
            if (produits.isEmpty()) {
                return new ResponseEntity<>(
                    Map.of("message", "Aucun produit trouvé"), 
                    HttpStatus.OK
                );
            }
            return new ResponseEntity<>(produits, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("timestamp", new Date().getTime());
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("error", e.getMessage());
            response.put("path", "/api/produits");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // Obtenir un produit par ID
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Integer id) {
        return ResponseEntity.ok( produitService.getProduitById(id)); 
               
    }

    // Mettre à jour un produit
    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Integer id, @RequestBody Produit produit) {
        produit.setId(id);
        return ResponseEntity.ok(produitService.saveProduit(produit));
    }

    // Supprimer un produit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Integer id) {
        produitService.deleteProduit(id);
        return ResponseEntity.ok().build();
    }

    // Ajoutez un endpoint de test
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("ProduitController is working!");
    }
}
