// package com.fst.ArtSphere.entities;

// import java.util.List;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @NoArgsConstructor
// @AllArgsConstructor
// @Data
// @Table(name = "categorie")
// public class Categorie {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private int id;
//     private String nom;
//     private String description;

//     @OneToMany(mappedBy = "categorie" ,cascade = CascadeType.ALL )
//     private List<Produit> produits;

// }