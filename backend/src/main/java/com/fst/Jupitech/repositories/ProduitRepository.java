package com.fst.Jupitech.repositories;


// import com.fst.Jupitech.entities.Categorie;
import com.fst.Jupitech.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import com.fst.Jupitech.entities.User;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    // Recherche par title
    Optional<Produit> findBytitle(String title);
    
    // Recherche par prix
    List<Produit> findByPrixLessThan(Double prix);
    List<Produit> findByPrixGreaterThan(Double prix);
    List<Produit> findByPrixBetween(Double prixMin, Double prixMax);
    
    // Recherche par catégorie (utilise l'entité Categorie)
    // List<Produit> findByCategorie(Categorie categorie);
    
    // Recherche par user (vendeur/seller)
    List<Produit> findByseller(User user);

    
    // Recherche par title (insensible à la casse)
    List<Produit> findBytitleContainingIgnoreCase(String title);
    
    // Recherches combinées
    // List<Produit> findByCategorieAndPrixLessThan(Categorie categorie, Double prix);
    // List<Produit> findBysellerAndCategorie(User user, Categorie categorie);
   
    
    // Tri par prix
    List<Produit> findAllByOrderByPrixAsc();
    List<Produit> findAllByOrderByPrixDesc();
}
