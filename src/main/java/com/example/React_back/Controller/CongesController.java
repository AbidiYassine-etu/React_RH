package com.example.React_back.Controller;
import com.example.React_back.Models.*;
import com.example.React_back.Repository.CongesRepository;
import com.example.React_back.Repository.EmployeeRepository;
import com.example.React_back.Services.CongesService;
import com.example.React_back.Services.Impl.EmployeeServiceImpl;
import com.example.React_back.Services.Impl.NotificationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conges")  // Endpoint de base pour les congés
public class CongesController {

    @Autowired
    private CongesService congesService;

    @Autowired
    private CongesRepository congesRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/getAllConges")
    public List<Conges> getAllConges() {
        return congesRepository.findByValidatedFalse();
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
    public ResponseEntity<Conges> createConges(@RequestBody Conges conge)
    {
        // Fetch the employee from the database using the employee id embedded in the request body
        Long employeeIdFromRequest = conge.getEmployee().getId();

        // Fetch the employee from the database
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeIdFromRequest);
        if (!employeeOptional.isPresent()) {
            throw new RuntimeException("Employee not found.");
        }
        Employee employee = employeeOptional.get();
        notificationService.createNotification(employee, "conges");

        // Check if the employee has enough leave days
        int remainingDays = employee.getNbr_jours_restant();
        if (remainingDays < conge.getNbr_jour()) {
            throw new RuntimeException("You don't have enough leave days remaining.");
        }

        // Deduct the leave days from the employee's leave balance
        employee.setNbr_jours_restant(remainingDays - conge.getNbr_jour());
        employeeRepository.save(employee); // Update the employee's leave balance in the database


        Conges createdConges = congesService.addConges(conge);
        return new ResponseEntity<>(createdConges, HttpStatus.CREATED);
    }

    @PutMapping("/updateConges/{id}")
    public ResponseEntity<Conges> updateConges(@PathVariable int id, @RequestBody Conges conge) {
        Optional<Conges> existingConges = congesRepository.findById(id);
        if (existingConges.isPresent()) {
            Conges updatedConges = existingConges.get();
            updatedConges.setStatus(conge.getStatus());
            updatedConges.setPayant(conge.isPayant());
            updatedConges.setValidated(true);

            Conges savedConges = congesRepository.save(updatedConges);

            return new ResponseEntity<>(savedConges, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/deleteConges/{id}")
    public ResponseEntity<Void> deleteConges(@PathVariable int id) {
        congesService.deleteConges(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getCongesByEmployee/{id}")
    public List<Conges> getCongesByEmployee(@PathVariable int id) {
        return congesService.findByEmployeeId(id);
    }

    // Endpoint to generate PDF
    @GetMapping("/exportConges")
    public void exportAllConges(HttpServletResponse response) throws IOException {
        // Fetch all Conges from the database
        List<Conges> congesList = congesService.findAllConges();

        // Generate the Excel file using the ExcelGenerator class
        byte[] excelData = ExcelGenerator.generateCongesExcel(congesList);

        // Set response headers to prompt the download of the Excel file
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=conges.xlsx");
        response.getOutputStream().write(excelData);
    }


}
