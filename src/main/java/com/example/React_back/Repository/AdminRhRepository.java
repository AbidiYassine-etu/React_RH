package com.example.React_back.Repository;

import com.example.React_back.Models.Admin_RH;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRhRepository extends JpaRepository<Admin_RH,Long> {
    Admin_RH findByEmail(String email);
}
