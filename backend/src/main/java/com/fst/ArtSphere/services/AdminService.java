package com.fst.ArtSphere.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import com.fst.ArtSphere.entities.Produit;


@Service
public class AdminService {
    @Autowired
    private UserService userService;
    @Autowired
    private ProduitService produitService;
    @Autowired
    private CommandeService commandeService;
    // ... autres services

    // Implémentation des méthodes d'administration

    // Gestion du stock
    public Produit mettreAJourStock(int produitId, int nouvelleQuantite) {
        Produit produit = produitService.getProduitById(produitId);
           
        
        if (nouvelleQuantite < 0) {
            throw new IllegalArgumentException("The quantity could not be negative");
        }
        
        produit.setStock(nouvelleQuantite);
        return produitService.saveProduit(produit);
    }

    public List<Produit> getProduitsStockBas(int seuilMin) {
        return produitService.getAllProduits().stream()
            .filter(p -> p.getStock() <= seuilMin)
            .collect(Collectors.toList());
    }

    public Map<String, Object> getStatistiquesStock() {
        List<Produit> produits = produitService.getAllProduits();
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("totalProduits", produits.size());
        stats.put("produitsEnRupture", produits.stream().filter(p -> p.getStock() == 0).count());
        stats.put("stockTotal", produits.stream().mapToInt(Produit::getStock).sum());
        stats.put("stockMoyen", produits.stream().mapToInt(Produit::getStock).average().orElse(0));
        
        return stats;
    }
}