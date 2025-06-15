// package com.fst.ArtSphere.services;

// import com.fst.ArtSphere.entities.Categorie;
// import com.fst.ArtSphere.repositories.CategorieRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class CategorieService {

//     @Autowired
//     private CategorieRepository categorieRepository;

    
//     public Categorie createCategorie(Categorie categorie) {
//         return categorieRepository.save(categorie);
//     }

//     public List<Categorie> getAllCategories() {
//         return categorieRepository.findAll();
//     }

    
//     public Optional<Categorie> getCategorieById(int id) {
//         return categorieRepository.findById(id);
//     }

    
//     public Categorie updateCategorie(int id, Categorie categorieDetails) {
//         Optional<Categorie> categorie = categorieRepository.findById(id);
//         if (categorie.isPresent()) {
//             Categorie existingCategorie = categorie.get();
//             existingCategorie.setNom(categorieDetails.getNom());
//             existingCategorie.setDescription(categorieDetails.getDescription());
//             return categorieRepository.save(existingCategorie);
//         }
//         return null;
//     }


//     public void deleteCategorie(int  id) {
//         categorieRepository.deleteById(id);
//     }
// } 