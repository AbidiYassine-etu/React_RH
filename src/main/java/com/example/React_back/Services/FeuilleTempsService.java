package com.example.React_back.Services;

import com.example.React_back.Models.Feuille_Temps;

import java.time.LocalTime;
import java.util.List;

public interface FeuilleTempsService {
    Feuille_Temps findFeuilleByID(int id); // Récupérer une feuille de temps par ID
    Feuille_Temps addFeuille(Feuille_Temps feuille); // Ajouter une feuille de temps
    Feuille_Temps updateFeuille(Feuille_Temps feuille); // Mettre à jour une feuille de temps
    void deleteFeuille(int id); // Supprimer une feuille de temps
    List<Feuille_Temps> findAllFeuilles(); // Récupérer toutes les feuilles de temps

    // Méthodes spécifiques
    Feuille_Temps updateHeuresTravaillées(int id, LocalTime heureDebut, LocalTime heureFin); // Mettre à jour les heures (heure de début et de fin)
    Feuille_Temps validerFeuille(int id); // Valider une feuille de temps
    Feuille_Temps corrigerHeures(int id, LocalTime heureDebut, LocalTime heureFin); // Corriger les heures de début et de fin
}
