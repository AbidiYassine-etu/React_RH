package com.example.React_back.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="congés")
public class Conges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String date_Debut;
    private String date_Fin;
    private String departement;
    private int nbr_jour;
    private boolean payant;
    private String description;
    private boolean validated = false;

    @Enumerated(EnumType.STRING)
    private Absence type;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
