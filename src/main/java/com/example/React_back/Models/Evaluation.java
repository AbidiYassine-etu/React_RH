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
@Table(name = "évaluation" )
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String date_Eval;
    private String note_Eval;
    private String commantaire;

    @ManyToOne
    @JoinColumn(name = "employée_id")
    private Employée employee;

    @ManyToOne
    @JoinColumn(name = "rh_id")
    private Admin_RH adminRH;
}
