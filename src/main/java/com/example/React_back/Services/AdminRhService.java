package com.example.React_back.Services;

import com.example.React_back.Models.Admin_RH;
import com.example.React_back.Security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface AdminRhService {
    Admin_RH findAdminById(Long id);
    Admin_RH addAdmin(Admin_RH admin);
    Admin_RH updateAdmin(Long id ,Admin_RH admin);
    void deleteAdmin(Long id);
    List<Admin_RH> findAllAdmins();
}
