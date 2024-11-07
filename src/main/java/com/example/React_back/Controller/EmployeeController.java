package com.example.React_back.Controller;


import com.example.React_back.Models.Employee;
import com.example.React_back.Services.Impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return employeeService.updateEmp(employee);
    }

    @DeleteMapping("/deleteEmp/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmp(id);
    }

    /*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmployeeController.LoginRequest loginRequest) {
        try {
            String token = employeeService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(new EmployeeController.JwtResponse(token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }

    public static class JwtResponse {
        private String token;
        public JwtResponse(String token) {
            this.token = token;
        }
        public String getToken() {
            return token;
        }
    }*/

}
