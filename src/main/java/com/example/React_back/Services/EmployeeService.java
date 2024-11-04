package com.example.React_back.Services;

import com.example.React_back.Models.Employee;

import java.util.List;

public interface EmployeeService {
    Employee findEmpByID(Long id);
    Employee addEmp(Employee user);
    Employee updateEmp(Employee user);
    void deleteEmp(Long id);
    List<Employee> findAllEmp();
}
