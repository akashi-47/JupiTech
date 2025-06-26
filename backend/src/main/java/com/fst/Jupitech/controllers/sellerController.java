package com.fst.Jupitech.controllers;

import com.fst.Jupitech.dto.ProductDTO;
import com.fst.Jupitech.entities.Produit;
import com.fst.Jupitech.services.SellerService;
import com.fst.Jupitech.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/sellers")
@CrossOrigin(origins = "http://localhost:3000")
public class SellerController {

    @Autowired
    private SellerService sellerService;
    
    @GetMapping("/{id}/produits")
    public ResponseEntity<List<ProductDTO>> getProduitsByseller(@PathVariable int id) {
        List<ProductDTO> produits = sellerService.getProduitsBysellerId(id);
        return new ResponseEntity<>(produits, HttpStatus.OK);
    }


   
} 