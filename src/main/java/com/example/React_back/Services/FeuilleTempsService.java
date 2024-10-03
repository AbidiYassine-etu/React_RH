package com.example.React_back.Services;

import com.example.React_back.Models.Feuille_Temps;

import java.util.List;

public interface FeuilleTempsService {
    Feuille_Temps findFeuilleByID(int id);
    Feuille_Temps addFeuille(Feuille_Temps feuille);
    Feuille_Temps updateFeuille(Feuille_Temps feuille);
    void deleteFeuille(int id);
    List<Feuille_Temps> findAllFeuilles();
}
