package com.example.React_back.Controller;

import com.example.React_back.Models.Conges;
import com.example.React_back.Services.CongesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conges")  // Endpoint de base pour les congés
public class CongesController {

    @Autowired
    private CongesService congesService;

    @GetMapping("/getAllConges")
    public List<Conges> getAllConges() {
        return congesService.findAllConges();
    }

    @GetMapping("/getCongesById/{id}")
    public ResponseEntity<Conges> getCongesById(@PathVariable int id) {
        Conges conge = congesService.findCongesByID(id);
        if (conge != null) {
            return new ResponseEntity<>(conge, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addConges")
    public ResponseEntity<Conges> createConges(@RequestBody Conges conge) {
        Conges createdConges = congesService.addConges(conge);
        return new ResponseEntity<>(createdConges, HttpStatus.CREATED);
    }

    @PutMapping("/updateConges/{id}")
    public ResponseEntity<Conges> updateConges(@PathVariable int id, @RequestBody Conges conge) {
        conge.setId(id);
        Conges updatedConges = congesService.updateConges(conge);
        if (updatedConges != null) {
            return new ResponseEntity<>(updatedConges, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteConges/{id}")
    public ResponseEntity<Void> deleteConges(@PathVariable int id) {
        congesService.deleteConges(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
