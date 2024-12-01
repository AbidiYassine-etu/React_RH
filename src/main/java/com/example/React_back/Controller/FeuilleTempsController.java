package com.example.React_back.Controller;

import com.example.React_back.Models.Feuille_Temps;
import com.example.React_back.Services.FeuilleTempsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feuille-temps")  // Endpoint de base pour les feuilles de temps
public class FeuilleTempsController {

    @Autowired
    private FeuilleTempsService feuilleTempsService;

    @GetMapping
    public List<Feuille_Temps> getAllFeuilles() {
        return feuilleTempsService.findAllFeuilles();  // Récupérer toutes les feuilles de temps
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feuille_Temps> getFeuilleById(@PathVariable int id) {
        Feuille_Temps feuille = feuilleTempsService.findFeuilleByID(id);
        if (feuille != null) {
            return new ResponseEntity<>(feuille, HttpStatus.OK);  // Retourner la feuille trouvée
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Retourner 404 si pas trouvée
        }
    }

    @PostMapping
    public ResponseEntity<Feuille_Temps> createFeuille(@RequestBody Feuille_Temps feuille) {
        Feuille_Temps createdFeuille = feuilleTempsService.addFeuille(feuille);
        return new ResponseEntity<>(createdFeuille, HttpStatus.CREATED);  // Retourner 201 pour une création réussie
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feuille_Temps> updateFeuille(@PathVariable int id, @RequestBody Feuille_Temps feuille) {
        feuille.setId(id);  // Mettre à jour l'ID de la feuille
        Feuille_Temps updatedFeuille = feuilleTempsService.updateFeuille(feuille);
        if (updatedFeuille != null) {
            return new ResponseEntity<>(updatedFeuille, HttpStatus.OK);  // Retourner la feuille mise à jour
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Retourner 404 si pas trouvée
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeuille(@PathVariable int id) {
        feuilleTempsService.deleteFeuille(id);  // Supprimer la feuille par ID
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Retourner 204 pour une suppression réussie
    }
}
