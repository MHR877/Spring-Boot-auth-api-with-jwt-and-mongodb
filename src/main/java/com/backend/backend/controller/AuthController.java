package com.backend.backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.model.Club;
import com.backend.backend.model.User;
import com.backend.backend.service.AuthService;

class Login {
    private String email;
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login, HttpServletResponse response) {
        return authService.login(login.getEmail(), login.getPassword(), response);
    }


    public static class Token {
        private String token;
    
        // Default constructor is required for deserialization
        public Token() {
            // Default constructor
            
        }
    
        public Token(String token) {
            this.token = token;
        }
    
        public String getToken() {
            return token;
        }
    
        public void setToken(String token) {
            this.token = token;
        }
    }
    

    @PostMapping("/verify-token")
    public ResponseEntity<?> verifyToken(@RequestBody Token token) {
        return authService.verifyToken(token.getToken());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        return authService.logout(response);
    }

    @GetMapping("/is-login")
    public ResponseEntity<?> isLogin(HttpServletRequest request) {
        return authService.isLogin(request);
    }

    @PostMapping("/register-user")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return authService.registerUser(user);
    }

    @PostMapping("/register-club")
    public ResponseEntity<?> registerClub(@RequestBody Club club) {
        return authService.registerClub(club);
    }
}
