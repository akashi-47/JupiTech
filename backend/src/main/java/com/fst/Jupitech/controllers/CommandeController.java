package com.fst.Jupitech.controllers;

import com.fst.Jupitech.entities.Commande;
import com.fst.Jupitech.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

@RestController
@RequestMapping("/api/commandes")
@CrossOrigin("*")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

  

    @GetMapping("/historique/{userId}")
    public ResponseEntity<List<Commande>> getHistoriqueCommandes(@PathVariable int userId) {
        List<Commande> commandes = commandeService.getHistoriqueCommandes(userId);
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    }

    @GetMapping("/historique/{userId}/etat/{etat}")
    public ResponseEntity<List<Commande>> getHistoriqueParEtat(
            @PathVariable int userId,
            @PathVariable String etat) {
        try {
            List<Commande> commandes = commandeService.getHistoriqueCommandesParEtat(userId, etat);
            return new ResponseEntity<>(commandes, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{commandeId}/suivi/{userId}")
    public ResponseEntity<String> suiviCommande(
            @PathVariable int commandeId,
            @PathVariable int userId) {
        try {
            String suivi = commandeService.suiviCommande(commandeId, userId);
            return new ResponseEntity<>(suivi, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{commandeId}/etat")
    public ResponseEntity<Commande> updateEtatCommande(
            @PathVariable int commandeId,
            @RequestBody Map<String, String> etatMap) {
        try {
            String nouvelEtat = etatMap.get("etat");
            Commande commande = commandeService.updateEtatCommande(commandeId, nouvelEtat);
            return new ResponseEntity<>(commande, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{commandeId}/annuler")
    public ResponseEntity<Void> annulerCommande(@PathVariable int commandeId) {
        try {
            commandeService.annulerCommande(commandeId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{commandeId}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable int commandeId) {
        try {
            Commande commande = commandeService.getCommandeById(commandeId);
            return new ResponseEntity<>(commande, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/historique/{userId}/periode")
    public ResponseEntity<List<Commande>> getHistoriqueParPeriode(
            @PathVariable int userId,
            @RequestParam String debut,
            @RequestParam String fin) {
        try {
            LocalDate dateDebut = LocalDate.parse(debut);
            LocalDate dateFin = LocalDate.parse(fin);
            List<Commande> commandes = commandeService.getHistoriqueCommandesParPeriode(userId, dateDebut, dateFin);
            return new ResponseEntity<>(commandes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("CommandeController is working!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Commande>> getAllCommandes() {
        try {
            List<Commande> commandes = commandeService.getAllCommandes();
            return new ResponseEntity<>(commandes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/test-post")
    public ResponseEntity<String> testPost(@RequestParam int userId) {
        return ResponseEntity.ok("POST test successful with userId: " + userId);
    }

} 