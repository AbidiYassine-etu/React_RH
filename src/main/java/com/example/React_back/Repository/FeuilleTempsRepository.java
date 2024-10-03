package com.example.React_back.Repository;

import com.example.React_back.Models.Feuille_Temps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeuilleTempsRepository extends JpaRepository<Feuille_Temps, Integer> {
    
}
