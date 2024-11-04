package com.example.React_back.Controller;

import com.example.React_back.Models.Evaluation;
import com.example.React_back.Services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluations")  // Endpoint de base pour les évaluations
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/allEvaluations")
    public List<Evaluation> getAllEvaluations() {
        return evaluationService.findAllEvaluations();
    }

    @GetMapping("/getEvaluationById/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable int id) {
        Evaluation evaluation = evaluationService.findEvaluationByID(id);
        if (evaluation != null) {
            return new ResponseEntity<>(evaluation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addEvaluation")
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody Evaluation evaluation) {
        Evaluation createdEvaluation = evaluationService.addEvaluation(evaluation);
        return new ResponseEntity<>(createdEvaluation, HttpStatus.CREATED);
    }

    @PutMapping("/updateEvaluation/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(@PathVariable int id, @RequestBody Evaluation evaluation) {
        evaluation.setId(id);
        Evaluation updatedEvaluation = evaluationService.updateEvaluation(evaluation);
        if (updatedEvaluation != null) {
            return new ResponseEntity<>(updatedEvaluation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteEvaluation/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable int id) {
        evaluationService.deleteEvaluation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
