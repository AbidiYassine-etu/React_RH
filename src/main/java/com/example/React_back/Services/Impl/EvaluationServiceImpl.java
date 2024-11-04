package com.example.React_back.Services.Impl;

import com.example.React_back.Models.Evaluation;
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

    @Override
    public Evaluation findEvaluationByID(int id) {
        Optional<Evaluation> evaluation = evaluationRepository.findById(id);
        return evaluation.orElse(null);
    }

    @Override
    public Evaluation addEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public Evaluation updateEvaluation(Evaluation evaluation) {
        if (evaluationRepository.existsById(evaluation.getId())) {
            return evaluationRepository.save(evaluation);
        }
        return null;
    }

    @Override
    public void deleteEvaluation(int id) {
        if (evaluationRepository.existsById(id)) {
            evaluationRepository.deleteById(id);
        }
    }

    @Override
    public List<Evaluation> findAllEvaluations() {
        return evaluationRepository.findAll();
    }
}
