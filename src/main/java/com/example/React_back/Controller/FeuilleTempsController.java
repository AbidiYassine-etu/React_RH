package com.example.React_back.Controller;

import com.example.React_back.Models.Feuille_Temps;
import com.example.React_back.Services.FeuilleTempsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/feuille-temps")
public class FeuilleTempsController {

    @Autowired
    private FeuilleTempsService feuilleTempsService;

    @GetMapping
    public List<Feuille_Temps> getAllFeuilles() {
        return feuilleTempsService.findAllFeuilles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feuille_Temps> getFeuilleById(@PathVariable int id) {
        Feuille_Temps feuille = feuilleTempsService.findFeuilleByID(id);
        if (feuille != null) {
            return new ResponseEntity<>(feuille, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Feuille_Temps> createFeuille(@RequestBody Feuille_Temps feuille) {
        Feuille_Temps createdFeuille = feuilleTempsService.addFeuille(feuille);
        return new ResponseEntity<>(createdFeuille, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/heures")
    public ResponseEntity<Feuille_Temps> updateHeuresTravaillées(
            @PathVariable int id,
            @RequestParam String heureDebut,
            @RequestParam String heureFin
    ) {
        try {
            LocalTime debut = LocalTime.parse(heureDebut);
            LocalTime fin = LocalTime.parse(heureFin);

            Feuille_Temps updatedFeuille = feuilleTempsService.updateHeuresTravaillées(id, debut, fin);
            if (updatedFeuille != null) {
                return new ResponseEntity<>(updatedFeuille, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/valider")
    public ResponseEntity<Feuille_Temps> validerFeuille(@PathVariable int id) {
        Feuille_Temps validatedFeuille = feuilleTempsService.validerFeuille(id);
        if (validatedFeuille != null) {
            return new ResponseEntity<>(validatedFeuille, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/corriger")
    public ResponseEntity<Feuille_Temps> corrigerFeuille(
            @PathVariable int id,
            @RequestParam String heureDebut,
            @RequestParam String heureFin
    ) {
        try {
            LocalTime debut = LocalTime.parse(heureDebut);
            LocalTime fin = LocalTime.parse(heureFin);

            Feuille_Temps correctedFeuille = feuilleTempsService.corrigerHeures(id, debut, fin);
            if (correctedFeuille != null) {
                return new ResponseEntity<>(correctedFeuille, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeuille(@PathVariable int id) {
        feuilleTempsService.deleteFeuille(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
