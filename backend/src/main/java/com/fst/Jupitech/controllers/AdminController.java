package com.fst.Jupitech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import com.fst.Jupitech.entities.Produit;
import com.fst.Jupitech.services.AdminService;
@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')") // Sécurité Spring
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    // Mettre à jour le stock d'un produit
    // @PutMapping("/produits/{id}/stock")
    // public ResponseEntity<?> updateStock(
    //         @PathVariable("id") int produitId,
    //         @RequestParam("quantite") int nouvelleQuantite) {
    //     try {
    //         Produit produit = adminService.mettreAJourStock(produitId, nouvelleQuantite);
    //         return ResponseEntity.ok(produit);
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest()
    //             .body(Map.of("error", e.getMessage()));
    //     }
    // }

    // Obtenir la liste des produits avec un stock bas
    @GetMapping("/produits/stock-bas")
    public ResponseEntity<List<Produit>> getProduitsStockBas(
            @RequestParam(defaultValue = "5") int seuil) {
        return ResponseEntity.ok(adminService.getProduitsStockBas(seuil));
    }

    // Obtenir les statistiques du stock
    @GetMapping("/stock/statistiques")
    public ResponseEntity<Map<String, Object>> getStatistiquesStock() {
        return ResponseEntity.ok(adminService.getStatistiquesStock());
    }
    
}