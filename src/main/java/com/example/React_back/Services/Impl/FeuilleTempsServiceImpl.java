package com.example.React_back.Services.Impl;

import com.example.React_back.Models.Feuille_Temps;
import com.example.React_back.Repository.FeuilleTempsRepository;
import com.example.React_back.Services.FeuilleTempsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeuilleTempsServiceImpl implements FeuilleTempsService {

    @Autowired
    private FeuilleTempsRepository feuilleTempsRepository;

    @Override
    public Feuille_Temps findFeuilleByID(int id) {
        Optional<Feuille_Temps> feuille = feuilleTempsRepository.findById(id);
        return feuille.orElse(null);
    }

    @Override
    public Feuille_Temps addFeuille(Feuille_Temps feuille) {
        return feuilleTempsRepository.save(feuille);
    }

    @Override
    public Feuille_Temps updateFeuille(Feuille_Temps feuille) {
        if (feuilleTempsRepository.existsById(feuille.getId())) {
            return feuilleTempsRepository.save(feuille);
        }
        return null;
    }

    @Override
    public void deleteFeuille(int id) {
        if (feuilleTempsRepository.existsById(id)) {
            feuilleTempsRepository.deleteById(id);
        }
    }

    @Override
    public List<Feuille_Temps> findAllFeuilles() {
        return feuilleTempsRepository.findAll();
    }
}
