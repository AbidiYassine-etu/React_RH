package com.example.React_back.Models;

import java.io.ObjectInputFilter.Status;

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
@Table(name="conges")
public class Conges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String date_Debut;
    private String date_Fin;
    private int nbr_jour_Restant;
    private boolean payant;
    @Enumerated
    private Status statut;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "rh_id")
    private Admin_RH adminRH;
}
