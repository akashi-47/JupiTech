package com.fst.ArtSphere.repositories;


import com.fst.ArtSphere.entities.Categorie;
import com.fst.ArtSphere.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import com.fst.ArtSphere.entities.User;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    // Recherche par titre
    Optional<Produit> findByTitre(String titre);
    
    // Recherche par prix
    List<Produit> findByPrixLessThan(Double prix);
    List<Produit> findByPrixGreaterThan(Double prix);
    List<Produit> findByPrixBetween(Double prixMin, Double prixMax);
    
    // Recherche par catégorie (utilise l'entité Categorie)
    List<Produit> findByCategorie(Categorie categorie);
    
    // Recherche par user (vendeur/artiste)
    List<Produit> findByArtiste(User user);

    
    // Recherche par titre (insensible à la casse)
    List<Produit> findByTitreContainingIgnoreCase(String titre);
    
    // Recherches combinées
    List<Produit> findByCategorieAndPrixLessThan(Categorie categorie, Double prix);
    List<Produit> findByArtisteAndCategorie(User user, Categorie categorie);
   
    
    // Tri par prix
    List<Produit> findAllByOrderByPrixAsc();
    List<Produit> findAllByOrderByPrixDesc();
}
