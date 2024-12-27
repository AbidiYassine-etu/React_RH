package com.example.React_back.Services.Impl;

import com.example.React_back.Models.Evaluation;
import com.example.React_back.Models.Employee;
import com.example.React_back.Models.Admin_RH;
import com.example.React_back.Repository.AdminRhRepository;
import com.example.React_back.Repository.EmployeeRepository;
import com.example.React_back.Repository.EvaluationRepository;
import com.example.React_back.Services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AdminRhRepository adminRHRepository;

    @Override
    public Evaluation findEvaluationByID(int id) {
        return evaluationRepository.findById(id).orElse(null);
    }

    @Override
    public Evaluation addEvaluation(Long employeeId, Long adminRHId, Evaluation evaluation) {
        // Fetch employee and adminRH by their IDs
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        Optional<Admin_RH> adminRHOptional = adminRHRepository.findById(adminRHId);

        if (employeeOptional.isPresent() && adminRHOptional.isPresent()) {
            evaluation.setEmployee(employeeOptional.get());
            evaluation.setAdminRH(adminRHOptional.get());
            return evaluationRepository.save(evaluation);
        }

        return null;  // or throw an exception if needed
    }

    @Override
    public Evaluation updateEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public void deleteEvaluation(int id) {
        evaluationRepository.deleteById(id);
    }

    @Override
    public List<Evaluation> findAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public List<Evaluation> findEvaluationsByEmployee(Long employeeId) {
        return evaluationRepository.findByEmployeeId(employeeId);
    }
}
