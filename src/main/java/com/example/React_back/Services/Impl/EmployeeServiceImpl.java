package com.example.React_back.Services.Impl;

import com.example.React_back.Models.Employee;
import com.example.React_back.Repository.EmployeeRepository;
import com.example.React_back.Security.TokenProvider;
import com.example.React_back.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

 /*   public String login(String email, String rawPassword) {
        Employee emp = employeeRepository.findByEmail(email);

        if (emp == null) {
            throw new RuntimeException("Invalid email or password");
        }

        if (!passwordEncoder.matches(rawPassword, emp.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return tokenProvider.generateToken(emp.getEmail());
    }

    public boolean validateToken(String token, Employee employee) {
        return tokenProvider.validateToken(token, employee.getEmail());
    }*/

    @Override
    public Employee findEmpByID(Long id) {
        // Return employee or null if not found (no Optional)
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        return employee;
    }

    @Override
    public Employee addEmp(Employee employee) {
        // Save a new employee in the repository
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmp(Employee employee) {
        // Check if the employee exists by ID, if yes, update
        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElse(null);
        if (existingEmployee == null) {
            throw new RuntimeException("Employee not found with id: " + employee.getId());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmp(Long id) {
        // Delete the employee if found, otherwise throw an exception
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);
        if (existingEmployee == null) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAllEmp() {
        // Return a list of all employees
        return employeeRepository.findAll();
    }
}
