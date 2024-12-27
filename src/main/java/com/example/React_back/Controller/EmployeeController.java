package com.example.React_back.Controller;


import com.example.React_back.Models.Conges;
import com.example.React_back.Models.Employee;
import com.example.React_back.Models.ExcelGenerator;
import com.example.React_back.Models.Presence;
import com.example.React_back.Repository.CongesRepository;
import com.example.React_back.Repository.EmployeeRepository;
import com.example.React_back.Repository.PresenceRepository;
import com.example.React_back.Services.Impl.EmployeeServiceImpl;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PresenceRepository presenceRepository;
    @Autowired
    private CongesRepository congesRepository;

    // Get all employees
    @GetMapping("/allEmp")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmp();
    }

    // Get an employee by ID
    @GetMapping("/findEmpById/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.findEmpByID(id);
    }

    // Add a new employee
    @PostMapping("/addEmp")
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeService.addEmp(employee);
    }

    // Update an existing employee
    @PutMapping("/updateEmp/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeService.updateEmp(employee);
    }

    @Transactional
    @DeleteMapping("/deleteEmp/{id}")
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


    @GetMapping("/presence/{employeeId}")
    public ResponseEntity<?> getEmployeePresence(@PathVariable Long employeeId) {
        List<Presence> presences = presenceRepository.findByEmployeeId(employeeId);
        return ResponseEntity.ok(presences);
    }

    // Endpoint to generate PDF
    @GetMapping("/exportPresence")
    public void exportAllConges(HttpServletResponse response) throws IOException {
        // Fetch all Conges from the database
        List<Presence> presencesList = presenceRepository.findAll();

        // Generate the Excel file using the ExcelGenerator class
        byte[] excelData = ExcelGenerator.generatePresenceExcel(presencesList);

        // Set response headers to prompt the download of the Excel file
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=presence.xlsx");

        // Write the Excel data to the response output stream
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(excelData);
            outputStream.flush();  // Ensure all data is written to the output stream
        }
    }

    @GetMapping("/exportPresence/{employeeId}")
    public void exportCongesForEmployee(@PathVariable Long employeeId, HttpServletResponse response) throws IOException {
        // Fetch Conges for the specific employee
        List<Presence> presencesList = presenceRepository.findByEmployeeId(employeeId);

        if (presencesList.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("No Presence found for the specified employee.");
            return;
        }

        // Generate the Excel file using the ExcelGenerator class
        byte[] excelData = ExcelGenerator.generatePresenceExcel(presencesList);

        // Set response headers to prompt the download of the Excel file
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=presence_employee_" + employeeId + ".xlsx");

        // Write the Excel data to the response output stream
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(excelData);
            outputStream.flush();  // Ensure all data is written to the output stream
        }
    }


}
