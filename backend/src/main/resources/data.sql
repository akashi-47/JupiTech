INSERT INTO seller (nom, prenom, email, password, adresse, businessName) 
VALUES 
('Dupont', 'Jean', 'jean.dupont@email.com', 'password123', '123 rue de l''Art, Paris', 'seller contemporain'),
('Martin', 'Sophie', 'sophie.martin@email.com', 'password456', '456 avenue des Arts, Lyon', 'Sculptrice moderne'),
('Bernard', 'Pierre', 'pierre.bernard@email.com', 'password789', '789 boulevard des Peintres, Marseille', 'Photographe');

INSERT INTO produit (nom, description, prix, seller_id) 
VALUES 
('Tableau abstrait', 'Peinture acrylique sur toile', 500.00, 1),
('Sculpture moderne', 'Bronze patiné', 1200.00, 2),
('Photo noir et blanc', 'Tirage argentique', 300.00, 3); package com.fst.Jupitech.controllers;

import com.fst.Jupitech.entities.Categorie;
import com.fst.Jupitech.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:3030")
public class CategorieController {

    @Autowired
    private CategorieService categorieService;

    
    @PostMapping
    public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
        Categorie newCategorie = categorieService.createCategorie(categorie);
        return new ResponseEntity<>(newCategorie, HttpStatus.CREATED);
    }

    
    @GetMapping
    public ResponseEntity<List<Categorie>> getAllCategories() {
        List<Categorie> categories = categorieService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategorieById(@PathVariable int id) {
        Optional<Categorie> categorie = categorieService.getCategorieById(id);
        return categorie.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategorie(@PathVariable int id, @RequestBody Categorie categorieDetails) {
        Categorie updatedCategorie = categorieService.updateCategorie(id, categorieDetails);
        if (updatedCategorie != null) {
            return new ResponseEntity<>(updatedCategorie, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorie(@PathVariable int id) {
        categorieService.deleteCategorie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
} 