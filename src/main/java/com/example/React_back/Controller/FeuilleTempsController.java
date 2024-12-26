package com.example.React_back.Controller;

import com.example.React_back.Models.Employee;
import com.example.React_back.Models.Feuille_Temps;
import com.example.React_back.Models.Status;
import com.example.React_back.Repository.EmployeeRepository;
import com.example.React_back.Services.FeuilleTempsService;
import com.example.React_back.Services.Impl.EmployeeServiceImpl;
import com.example.React_back.Services.Impl.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feuille-temps")  // Endpoint de base pour les feuilles de temps
public class FeuilleTempsController {

    @Autowired
    private FeuilleTempsService feuilleTempsService;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping
    public ResponseEntity<List<Feuille_Temps>> getAllFeuilles() {
        List<Feuille_Temps> feuilles = feuilleTempsService.findAllFeuilles()                .stream()
                .filter(feuille -> !feuille.isValidated())
                .collect(Collectors.toList());
        return new ResponseEntity<>(feuilles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feuille_Temps> getFeuilleById(@PathVariable int id) {
        Feuille_Temps feuille = feuilleTempsService.findFeuilleByID(id);
        if (feuille != null) {
            return new ResponseEntity<>(feuille, HttpStatus.OK);  // Retourner la feuille trouvée
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Retourner 404 si pas trouvée
        }
    }

    @PostMapping("/addFeuille/")
    public ResponseEntity<Feuille_Temps> createFeuille(@RequestBody Feuille_Temps feuille) {
        Feuille_Temps createdFeuille = feuilleTempsService.addFeuille(feuille);
        Long employeeId = (long) feuille.getEmployeeId();
        Employee employee = employeeServiceImpl.findEmpByID(employeeId);

        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if employee not found
        }

        // Create a notification
        notificationService.createNotification(employee, "feuille temps");

        return new ResponseEntity<>(createdFeuille, HttpStatus.CREATED);  // Retourner 201 pour une création réussie
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feuille_Temps> updateFeuille(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        // Find the existing Feuille Temps
        Optional<Feuille_Temps> optionalFeuille = Optional.ofNullable(feuilleTempsService.findFeuilleByID(id));
        if (optionalFeuille.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Feuille_Temps feuille = optionalFeuille.get();

        // Update specific fields based on the request body
        if (updates.containsKey("validated")) {
            feuille.setValidated((Boolean) updates.get("validated"));
        }
        if (updates.containsKey("status")) {
            feuille.setStatus(Status.valueOf((String) updates.get("status")));
        }

        // Save the updated Feuille Temps
        Feuille_Temps updatedFeuille = feuilleTempsService.updateFeuille(feuille);

        // Send notification if validation status changes or other critical updates occur
        if (updates.containsKey("validated") && feuille.isValidated()) {
            Long employeeId = Long.valueOf(feuille.getEmployeeId());
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

            // Notify the employee about the update
            String message = String.format("Your Feuille Temps (ID: %d) has been validated.", feuille.getId());
            notificationService.notifyEmployeeRequestChecked(employee, message);
        }

        return new ResponseEntity<>(updatedFeuille, HttpStatus.OK);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeuille(@PathVariable int id) {
        feuilleTempsService.deleteFeuille(id);  // Supprimer la feuille par ID
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Retourner 204 pour une suppression réussie
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Feuille_Temps>> getFeuillesByEmployeeId(@PathVariable int employeeId) {
        List<Feuille_Temps> feuilles = feuilleTempsService.findByEmployeeId(employeeId);
        if (feuilles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Aucun contenu trouvé
        }
        return new ResponseEntity<>(feuilles, HttpStatus.OK);  // Retourner les feuilles de temps de l'employé
    }
}
