package com.fst.ArtSphere.repositories;

import com.fst.ArtSphere.entities.User;
import com.fst.ArtSphere.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtisteRepository extends JpaRepository<User, Integer> {
    @Query("SELECT p FROM Produit p WHERE p.artiste.id = :artisteId")
    List<Produit> findProduitsByArtisteId(int artisteId);
    
    @Query("SELECT a FROM User a WHERE a.email = :email")
    Optional<User> findByEmail(String email);
} 