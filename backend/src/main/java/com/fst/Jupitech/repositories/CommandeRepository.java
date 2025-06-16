package com.fst.Jupitech.repositories;

import com.fst.Jupitech.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
 // Find all Commandes for a specific client, ordered by dateCommande descending
 List<Commande> findByClientIdOrderByDateCommandeDesc(int clientId);

 // Find a specific Commande by its ID and the associated client's ID
 Optional<Commande> findByIdAndClientId(int commandeId, int clientId);

 // Find all Commandes with a specific etatCommande
 List<Commande> findByEtatCommande(String etatCommande);

 // Find all Commandes for a specific client with a specific etatCommande, ordered by dateCommande descending
 List<Commande> findByClientIdAndEtatCommandeOrderByDateCommandeDesc(int clientId, String etatCommande);

 // Find all Commandes for a specific client within a date range, ordered by dateCommande descending
 List<Commande> findByClientIdAndDateCommandeBetweenOrderByDateCommandeDesc(int clientId, LocalDate debut, LocalDate fin); }