package com.example.React_back.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employée extends User{


    @OneToMany
    @JoinColumn(name = "conges")
    private List<Conges> congesList;

    @OneToMany
    @JoinColumn(name = "evaluation")
    private List<Evaluation> evaluationList;

    @OneToOne
    @JoinColumn(name = "feuille_temps")
    private  Feuille_Temps feuilleTemps;
}
