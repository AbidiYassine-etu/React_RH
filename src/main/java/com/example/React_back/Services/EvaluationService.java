package com.example.React_back.Services;

import com.example.React_back.Models.Evaluation;

import java.util.List;

public interface EvaluationService {
    Evaluation findEvaluationByID(int id);
    Evaluation addEvaluation(Evaluation evaluation);
    Evaluation updateEvaluation(Evaluation evaluation);
    void deleteEvaluation(int id);
    List<Evaluation> findAllEvaluations();
}
