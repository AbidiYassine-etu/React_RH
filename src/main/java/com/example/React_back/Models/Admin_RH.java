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
public class Admin_RH extends User{

    @OneToMany
    @JoinColumn(name = "feuille_id")
    private List<Feuille_Temps> feuille;

    @OneToMany
    @JoinColumn(name = "evaluation_id")
    private List<Evaluation> evaluation;

    @OneToMany
    @JoinColumn(name = "conges")
    private List<Conges> conges;
}
