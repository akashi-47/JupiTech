package com.fst.ArtSphere.controllers;

import com.fst.ArtSphere.entities.Produit;
import com.fst.ArtSphere.services.ArtisteService;
import com.fst.ArtSphere.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.fst.ArtSphere.entities.User;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/artistes")
@CrossOrigin(origins = "http://localhost:3000")
public class ArtisteController {

    @Autowired
    private ArtisteService artisteService;

  
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllArtistes() {
        List<User> artistes = artisteService.getAllArtistes();
        return new ResponseEntity<>(artistes, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getArtisteById(@PathVariable int id) {
        Optional<User> artiste = artisteService.getArtisteById(id);
        return artiste.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateArtiste(@PathVariable int id, @RequestBody User artisteDetails) {
        User updatedArtiste = artisteService.updateArtiste(id, artisteDetails);
        if (updatedArtiste != null) {
            return new ResponseEntity<>(updatedArtiste, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtiste(@PathVariable int  id) {
        artisteService.deleteArtiste(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
    @GetMapping("/{id}/produits")
    public ResponseEntity<List<Produit>> getProduitsByArtiste(@PathVariable int id) {
        List<Produit> produits = artisteService.getProduitsByArtisteId(id);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test() {
        return "ArtisteController is working!";
    }

   
} 