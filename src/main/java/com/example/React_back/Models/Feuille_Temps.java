package com.example.React_back.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


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

    private LocalDate date;
    private double hoursWorked;
    private String taskDescription;

    @Enumerated(EnumType.STRING)
    private status status;


    @Column(name = "employee_id")
    private int employeeId;
}
