package com.example.React_back.Services.Impl;

import com.example.React_back.Models.Admin_RH;
import com.example.React_back.Repository.AdminRhRepository;
import com.example.React_back.Security.TokenProvider;
import com.example.React_back.Services.AdminRhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRHServiceImpl implements AdminRhService {

    @Autowired
    private AdminRhRepository adminRHRepository;

    @Autowired
    private  TokenProvider tokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String email, String rawPassword) {
        // Check if the email exists
        Admin_RH adminRH = adminRHRepository.findByEmail(email);

        if (adminRH == null) {
            throw new RuntimeException("Invalid email or password"); // Throw custom exception for better handling
        }

        // Verify the password
        if (!passwordEncoder.matches(rawPassword, adminRH.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // If email and password are valid, generate and return the JWT token
        return tokenProvider.generateToken(adminRH.getEmail());
    }

    public boolean validateToken(String token, Admin_RH adminRH) {
        return tokenProvider.validateToken(token, adminRH.getEmail());
    }

    @Override
    public Admin_RH findAdminById(Long id) {
        return adminRHRepository.findById(id).orElse(null); // Adjust as needed
    }

    @Override
    public Admin_RH addAdmin(Admin_RH admin) {
        return adminRHRepository.save(admin);
    }

    @Override
    public Admin_RH updateAdmin(Long id, Admin_RH admin) {
        if (adminRHRepository.existsById(id)) {
            admin.setId(id);
            return adminRHRepository.save(admin);
        }
        return null;
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRHRepository.deleteById(id);
    }

    @Override
    public List<Admin_RH> findAllAdmins() {
        return adminRHRepository.findAll();
    }
}
