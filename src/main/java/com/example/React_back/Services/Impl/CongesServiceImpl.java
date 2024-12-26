package com.example.React_back.Services.Impl;

import com.example.React_back.Models.Conges;
import com.example.React_back.Repository.CongesRepository;
import com.example.React_back.Services.CongesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CongesServiceImpl implements CongesService {

    @Autowired
    private CongesRepository congesRepository;

    @Override
    public Conges findCongesByID(int id) {
        Optional<Conges> conge = congesRepository.findById(id);
        return conge.orElse(null);
    }

    @Override
    public Conges addConges(Conges conge) {
        return congesRepository.save(conge);
    }

    @Override
    public Conges updateConges(Conges conge) {
        if (congesRepository.existsById(conge.getId())) {
            return congesRepository.save(conge);
        }
        return null;
    }

    @Override
    public void deleteConges(int id) {
        if (congesRepository.existsById(id)) {
            congesRepository.deleteById(id);
        }
    }

    @Override
    public List<Conges> findAllConges() {
        return congesRepository.findAll();
    }

    @Override
    public List<Conges> findByEmployeeId(int employeeId) {
        return congesRepository.findByEmployeeId(employeeId);
    }

    @Override
    public List<Conges> getFilteredConges(Long empId, String department, String congesType, String startDate, String endDate) {
        return List.of();
    }



}
