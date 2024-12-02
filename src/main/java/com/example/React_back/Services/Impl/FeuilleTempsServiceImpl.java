package com.example.React_back.Services.Impl;

import com.example.React_back.Models.Feuille_Temps;
import com.example.React_back.Repository.FeuilleTempsRepository;
import com.example.React_back.Services.FeuilleTempsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class FeuilleTempsServiceImpl implements FeuilleTempsService {

    @Autowired
    private FeuilleTempsRepository feuilleTempsRepository;

    @Override
    public Feuille_Temps findFeuilleByID(int id) {
        return feuilleTempsRepository.findById(id).orElse(null);
    }

    @Override
    public Feuille_Temps addFeuille(Feuille_Temps feuille) {
        return feuilleTempsRepository.save(feuille);
    }

    @Override
    public Feuille_Temps updateFeuille(Feuille_Temps feuille) {
        return feuilleTempsRepository.save(feuille);
    }

    @Override
    public void deleteFeuille(int id) {
        feuilleTempsRepository.deleteById(id);
    }

    @Override
    public List<Feuille_Temps> findAllFeuilles() {
        return feuilleTempsRepository.findAll();
    }

    // Implémentation des méthodes spécifiques

    @Override
    public Feuille_Temps updateHeuresTravaillées(int id, LocalTime heureDebut, LocalTime heureFin) {
        Feuille_Temps feuilleTemps = findFeuilleByID(id);
        if (feuilleTemps != null) {
            feuilleTemps.setHeure_Debut(heureDebut);
            feuilleTemps.setHeure_Fin(heureFin);
            return feuilleTempsRepository.save(feuilleTemps);
        }
        return null;
    }

    @Override
    public Feuille_Temps validerFeuille(int id) {
        Feuille_Temps feuilleTemps = findFeuilleByID(id);
        if (feuilleTemps != null) {
            feuilleTemps.setValidée(true);
            return feuilleTempsRepository.save(feuilleTemps);
        }
        return null;
    }

    @Override
    public Feuille_Temps corrigerHeures(int id, LocalTime heureDebut, LocalTime heureFin) {
        Feuille_Temps feuilleTemps = findFeuilleByID(id);
        if (feuilleTemps != null && !feuilleTemps.isValidée()) {
            feuilleTemps.setHeure_Debut(heureDebut);
            feuilleTemps.setHeure_Fin(heureFin);
            return feuilleTempsRepository.save(feuilleTemps);
        }
        return null;
    }
}
