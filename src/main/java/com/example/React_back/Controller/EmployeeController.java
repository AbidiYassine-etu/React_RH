package com.example.React_back.Controller;


import com.example.React_back.Models.Employee;
import com.example.React_back.Services.Impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @DeleteMapping("/deleteEmp/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmp(id);
    }

}
