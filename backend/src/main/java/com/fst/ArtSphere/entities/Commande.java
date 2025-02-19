package com.fst.ArtSphere.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate dateCommande;
    private Double montantTotal;
    private String etatCommande;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;
       @ManyToMany
    @JoinTable(
        name = "commande_produit", 
        joinColumns = @JoinColumn(name = "commande_id"), 
        inverseJoinColumns = @JoinColumn(name = "produit_id") 
    )
    private List<Produit> produits;
  

    @OneToOne(mappedBy = "commande", cascade = CascadeType.ALL)
    private Paiement paiement;
}