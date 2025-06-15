package com.fst.ArtSphere.controllers;

import com.fst.ArtSphere.entities.Produit;
import com.fst.ArtSphere.services.sellerService;
import com.fst.ArtSphere.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.fst.ArtSphere.entities.User;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/sellers")
@CrossOrigin(origins = "http://localhost:3000")
public class sellerController {

    @Autowired
    private sellerService sellerService;

  
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllsellers() {
        List<User> sellers = sellerService.getAllsellers();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getsellerById(@PathVariable int id) {
        Optional<User> seller = sellerService.getsellerById(id);
        return seller.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateseller(@PathVariable int id, @RequestBody User sellerDetails) {
        User updatedseller = sellerService.updateseller(id, sellerDetails);
        if (updatedseller != null) {
            return new ResponseEntity<>(updatedseller, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteseller(@PathVariable int  id) {
        sellerService.deleteseller(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
    @GetMapping("/{id}/produits")
    public ResponseEntity<List<Produit>> getProduitsByseller(@PathVariable int id) {
        List<Produit> produits = sellerService.getProduitsBysellerId(id);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test() {
        return "sellerController is working!";
    }

   
} 