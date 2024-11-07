package com.example.React_back.Services.Impl;

import com.example.React_back.Models.Admin_RH;
import com.example.React_back.Models.Employee;
import com.example.React_back.Models.Roles;
import com.example.React_back.Models.User;
import com.example.React_back.Repository.AdminRhRepository;
import com.example.React_back.Repository.EmployeeRepository;
import com.example.React_back.Repository.UserRepository;
import com.example.React_back.Security.TokenProvider;
import com.example.React_back.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRhRepository rhRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public User findUserByID(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public String login(String email, String rawPassword, Roles role) {
        if (role == Roles.ADMIN_RH) {
            Admin_RH adminRH = rhRepository.findByEmail(email);

            if (adminRH == null) {
                throw new RuntimeException("Invalid email or password");
            }

            if (!passwordEncoder.matches(rawPassword, adminRH.getPassword())) {
                throw new RuntimeException("Invalid email or password");
            }

            return tokenProvider.generateToken(adminRH.getEmail());
        } else if (role == Roles.EMPLOYEE) {
            Employee emp = employeeRepository.findByEmail(email);

            if (emp == null) {
                throw new RuntimeException("Invalid email or password");
            }

            if (!passwordEncoder.matches(rawPassword, emp.getPassword())) {
                throw new RuntimeException("Invalid email or password");
            }

            return tokenProvider.generateToken(emp.getEmail());
        } else {
            throw new RuntimeException("Invalid role");
        }
    }

    public boolean validateToken(String token, String email, String role) {
        if ("admin_rh".equals(role)) {
            Admin_RH adminRH = rhRepository.findByEmail(email);
            return tokenProvider.validateToken(token, adminRH.getEmail());
        } else if ("employee".equals(role)) {
            Employee emp = employeeRepository.findByEmail(email);
            return tokenProvider.validateToken(token, emp.getEmail());
        } else {
            throw new RuntimeException("Invalid role");
        }
    }
}
