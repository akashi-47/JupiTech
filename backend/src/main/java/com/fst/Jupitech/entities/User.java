package com.fst.Jupitech.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fst.Jupitech.enums.RolesEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User  {
        
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String nom;
  private String prenom;
  private String password;
  private String email;
 private String address;

  private String businessname;
  @Enumerated(EnumType.STRING)
  private RolesEnum role;

  @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL )
  @JsonIgnore
  private List<Produit> produits;

  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Commande> commandes;

	@OneToMany(mappedBy = "client")
    private List<Notification> notifications;

}
