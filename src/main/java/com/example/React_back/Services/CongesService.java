package com.example.React_back.Services;

import com.example.React_back.Models.Conges;

import java.util.List;

public interface CongesService {
    Conges findCongesByID(int id);
    Conges addConges(Conges conge);
    Conges updateConges(Conges conge);
    void deleteConges(int id);
    List<Conges> findAllConges();
    List<Conges> findByEmployeeId(int employeeId);
}
