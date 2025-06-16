package com.fst.Jupitech.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import jakarta.persistence.CascadeType;

@Entity
@Data
@Table(name = "panier")
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User client;
    


    private boolean estActif;


    public int getId() {
        return id;
    }
    public Panier() {
        this.estActif = true;
    }

    public Panier(User user) {
        this();
        this.client= user;
    }
}


