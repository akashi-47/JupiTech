package com.fst.Jupitech.repositories;

import com.fst.Jupitech.entities.User;
import com.fst.Jupitech.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface sellerRepository extends JpaRepository<User, Integer> {
    @Query("SELECT p FROM Produit p WHERE p.seller.id = :sellerId")
    List<Produit> findProduitsBysellerId(int sellerId);
    
    @Query("SELECT a FROM User a WHERE a.email = :email")
    Optional<User> findByEmail(String email);
} 