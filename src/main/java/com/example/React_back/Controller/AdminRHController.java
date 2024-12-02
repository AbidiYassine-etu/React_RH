package com.example.React_back.Controller;

import com.example.React_back.Models.Admin_RH;
import com.example.React_back.Services.Impl.AdminRHServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rh")
public class AdminRHController {

    @Autowired
    private AdminRHServiceImpl adminRHService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/allRh")
    public List<Admin_RH> getAllAdmins() {
        return adminRHService.findAllAdmins();
    }

    @GetMapping("/findRhById/{id}")
    public ResponseEntity<Admin_RH> getAdminById(@PathVariable Long id) {
        Admin_RH admin = adminRHService.findAdminById(id);
        return admin != null ? ResponseEntity.ok(admin) : ResponseEntity.notFound().build();
    }

    @PostMapping("/addRh")
    public Admin_RH createAdmin(@RequestBody Admin_RH admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRHService.addAdmin(admin);
    }

    @PutMapping("/updateRh/{id}")
    public ResponseEntity<Admin_RH> updateAdmin(@PathVariable Long id, @RequestBody Admin_RH admin) {
        Admin_RH updatedAdmin = adminRHService.updateAdmin(id, admin);
        return updatedAdmin != null ? ResponseEntity.ok(updatedAdmin) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteRh/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        adminRHService.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = adminRHService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(new JwtResponse(token));
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
    }

}
