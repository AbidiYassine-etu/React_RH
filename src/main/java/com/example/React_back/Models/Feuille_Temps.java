package com.example.React_back.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
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

    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private  Work Time;
    private String taskDescription;
    private LocalTime startTime;
    private LocalTime endTime;

    private boolean validated = false;

    @Enumerated(EnumType.STRING)
    private Status status;


    @Column(name = "employee_id")
    private int employeeId;
}
