package com.example.React_back.Repository;

import com.example.React_back.Models.Employee;
import com.example.React_back.Models.Feuille_Temps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeuilleTempsRepository extends JpaRepository<Feuille_Temps, Integer> {
    List<Feuille_Temps> findByEmployeeId(int employeeId);
    List<Feuille_Temps> findByStatus(Feuille_Temps status);
}
