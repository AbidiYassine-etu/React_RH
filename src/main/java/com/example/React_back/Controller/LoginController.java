package com.example.React_back.Controller;


import com.example.React_back.DTO.LoginDTO;
import com.example.React_back.DTO.athenticationDTO;
import com.example.React_back.Models.User;
import com.example.React_back.Security.TokenProvider;
import com.example.React_back.Services.Impl.UserServicesImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final UserServicesImpl userServicesImpl;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public LoginController(UserServicesImpl userServicesImpl, PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.userServicesImpl = userServicesImpl;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginRequest) {
        User user = userServicesImpl.findByEmail(loginRequest.getEmail());

        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = tokenProvider.generateToken(user);
            return ResponseEntity.ok(new athenticationDTO(token,user.getRole().name()));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

    }

    private String authenticateUser(String email, String password) {
        return "dummyToken";
    }
}
