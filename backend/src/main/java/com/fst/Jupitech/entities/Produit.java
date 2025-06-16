package com.fst.Jupitech.entities;

import java.util.List;

import com.fst.Jupitech.enums.CategorieEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private double prix;
    private String status;
    private int stock;
    private String imageURL;
    private String quantite;
 
    private CategorieEnum categorie;

    @ManyToMany(mappedBy = "produits")
    private List<Commande> commandes;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

  

}
