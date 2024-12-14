package com.example.React_back.Models;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee extends User{


    @OneToMany
    @JoinColumn(name = "conges")
    private List<Conges> congesList;

    @OneToMany
    @JoinColumn(name = "evaluation")
    private List<Evaluation> evaluationList;

    @OneToMany
    @JoinColumn(name = "feuille_temps")
    private List<Feuille_Temps> feuilleTemps;
}
