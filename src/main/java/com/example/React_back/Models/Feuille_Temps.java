package com.example.React_back.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "rh_id")
    private Admin_RH Admin_rh;

    @OneToOne
    @JoinColumn(name = "employée_id")
    private Employée employée_id;
}
