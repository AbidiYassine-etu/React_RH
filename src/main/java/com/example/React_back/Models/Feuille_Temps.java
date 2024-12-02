package com.example.React_back.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "feuilles")
public class Feuille_Temps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String date_Feuille;

    private String sujet_Feuille;

    private String commantaire_feuille;

    // Remplacement de heures_Travaillées par heure_Debut et heure_Fin
    private LocalTime heure_Debut;

    private LocalTime heure_Fin;

    private boolean validée;

    @ManyToOne
    @JoinColumn(name = "rh_id")
    private Admin_RH Admin_rh;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee_id;
}
