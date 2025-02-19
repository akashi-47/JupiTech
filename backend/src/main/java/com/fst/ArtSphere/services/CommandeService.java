package com.fst.ArtSphere.services;

import com.fst.ArtSphere.entities.Commande;
import com.fst.ArtSphere.entities.Produit;

import com.fst.ArtSphere.entities.User;

import com.fst.ArtSphere.repositories.CommandeRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fst.ArtSphere.services.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommandeService {
    private final CommandeRepository commandeRepository;
 
    private final UserService userService;
  

    @Autowired
    public CommandeService(
        CommandeRepository commandeRepository,
       
        UserService userService
    
    ) {
        this.commandeRepository = commandeRepository;
      
        this.userService = userService;
      
    }
    
    /*public Commande creerCommande(int userId) {
        // Récupérer l'utilisateur
        User user = userService.getUserById(userId);
        
        // Récupérer les lignes du panier actif
        List<LignePanier> lignesPanier = lignePanierService.getLignesPanier(
            panierService.getPanierActifByUserId(userId).getId()
        );
        
        if (lignesPanier.isEmpty()) {
            throw new RuntimeException("Le panier est vide");
        }

        // Calculer le montant total
        double montantTotal = lignesPanier.stream()
            .mapToDouble(lp -> lp.getQuantite() * lp.getProduit().getPrix())
            .sum();

        // Créer la nouvelle commande
        Commande commande = new Commande();
        commande.setUser(user);
        commande.setDateCommande(LocalDate.now());
        commande.setEtatCommande("EN_ATTENTE");
        commande.setMontantTotal(montantTotal);
        commande.setLignesPaniers(lignesPanier);

        // Associer les lignes de panier à la commande
        lignesPanier.forEach(lp -> lp.setCommande(commande));
        
        return commandeRepository.save(commande);
    }*/

    public List<Commande> getHistoriqueCommandes(int userId) {
        return commandeRepository.findByClientIdOrderByDateCommandeDesc(userId);
    }

    public Commande getCommandeById(int commandeId, int userId) {
        return commandeRepository.findByIdAndClientId(commandeId, userId)
            .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
    }

    public Commande updateEtatCommande(int commandeId, String nouvelEtat) {
        Commande commande = commandeRepository.findById(commandeId)
            .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        
        if (!isEtatValide(nouvelEtat)) {
            throw new IllegalArgumentException("État de commande invalide");
        }
        
        commande.setEtatCommande(nouvelEtat);
        return commandeRepository.save(commande);
    }

    private boolean isEtatValide(String etat) {
        return etat != null && (
            etat.equals("EN_ATTENTE") ||
            etat.equals("CONFIRMEE") ||
            etat.equals("EN_LIVRAISON") ||
            etat.equals("LIVREE") ||
            etat.equals("ANNULEE")
        );
    }

    public List<Commande> getCommandesParEtat(String etat) {
        return commandeRepository.findByEtatCommande(etat);
    }

    public void annulerCommande(int commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
            .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
            
        if (!commande.getEtatCommande().equals("EN_ATTENTE")) {
            throw new IllegalStateException("Impossible d'annuler une commande qui n'est pas en attente");
        }
        
        commande.setEtatCommande("ANNULEE");
        commandeRepository.save(commande);
    }
    public Commande getCommandeById(int commandeId) {
        return commandeRepository.findById(commandeId)
            .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
    }

    


    public List<Commande> getHistoriqueCommandesParEtat(int userId, String etat) {
        if (!isEtatValide(etat)) {
            throw new IllegalArgumentException("État de commande invalide");
        }
        return commandeRepository.findByClientIdAndEtatCommandeOrderByDateCommandeDesc(userId, etat);
    }

    public List<Commande> getHistoriqueCommandesParPeriode(int userId, LocalDate debut, LocalDate fin) {
        return commandeRepository.findByClientIdAndDateCommandeBetweenOrderByDateCommandeDesc(
            userId, debut, fin);
    }

    public String suiviCommande(int commandeId, int userId) {
        Commande commande = commandeRepository.findByIdAndClientId(commandeId, userId)
            .orElseThrow(() -> new RuntimeException("Commande non trouvée"));  
        String etat = commande.getEtatCommande();
        String message;
        
        switch (etat) {
            case "EN_ATTENTE":
                message = "Votre commande est en attente de confirmation";
                break;
            case "CONFIRMEE":
                message = "Votre commande a été confirmée et est en cours de préparation";
                break;
            case "EN_LIVRAISON":
                message = "Votre commande est en cours de livraison";
                break;
            case "LIVREE":
                message = "Votre commande a été livrée le " + commande.getDateCommande();
                break;
            case "ANNULEE":
                message = "Votre commande a été annulée";
                break;
            default:
                message = "État de la commande inconnu";
        }
        
        return message + " (Commande #" + commandeId + ")";
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    // Convertir un panier en commande
    


// Calculer le montant total d'une commande

} 