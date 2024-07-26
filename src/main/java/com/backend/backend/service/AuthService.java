package com.backend.backend.service;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.backend.model.Admin;
import com.backend.backend.model.Club;
import com.backend.backend.model.User;
import com.backend.backend.repository.AdminRepository;
import com.backend.backend.repository.ClubRepository;
import com.backend.backend.repository.UserRepository;
import com.backend.backend.service.AuthService.ApiResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private ClubService clubService;

    private static final String SECRET_KEY = "my-strong-secret-key-for-jwt";

    public ResponseEntity<?> login(String email, String password, HttpServletResponse response) {
        User user = userRepository.findByEmail(email);
        Club club = clubRepository.findByEmail(email);
        Admin admin = adminRepository.findByEmail(email);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return generateJwtAndSetCookie(user, "ROLE_USER", response);
        }

        if (club != null && passwordEncoder.matches(password, club.getPassword())) {
            return generateJwtAndSetCookie(club, "ROLE_CLUB", response);
        }

        if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
            return generateJwtAndSetCookie(admin, "ROLE_ADMIN", response);
        }

        return new ResponseEntity<>(new ApiResponse("Invalid email or password", null), HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> verifyToken(String token) {
        if (token == null || token.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse("Token is missing", null), HttpStatus.UNAUTHORIZED);
        }

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();
            String role = claims.get("role", String.class);

            Object user = getUserByEmailAndRole(email, role);

            if (user == null) {
                return new ResponseEntity<>(new ApiResponse("User not found", null), HttpStatus.UNAUTHORIZED);
            }

            return new ResponseEntity<>(new ApiResponse("Token is valid", user), HttpStatus.OK);

        } catch (JwtException | IllegalArgumentException e) {
            System.err.println("JWT Exception: " + e.getMessage());
            return new ResponseEntity<>(new ApiResponse("Invalid token", null), HttpStatus.UNAUTHORIZED);
        }
    }

    private Object getUserByEmailAndRole(String email, String role) {
        switch (role) {
            case "ROLE_USER":
                return userRepository.findByEmail(email);
            case "ROLE_CLUB":
                return clubRepository.findByEmail(email);
            case "ROLE_ADMIN":
                return adminRepository.findByEmail(email);
            default:
                return null;
        }
    }

    private ResponseEntity<?> generateJwtAndSetCookie(Object user, String role, HttpServletResponse response) {
        String jwt = Jwts.builder()
                .setSubject(role.equals("ROLE_USER") ? ((User) user).getEmail()
                        : role.equals("ROLE_CLUB") ? ((Club) user).getEmail() : ((Admin) user).getEmail())
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        Cookie cookie = new Cookie("token", jwt);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(86400); 
        response.addCookie(cookie);

        return new ResponseEntity<>(new ApiResponse("Successful login as " + role, user), HttpStatus.OK);
    }

    public ResponseEntity<?> registerClub(Club club) {
        club.setPassword(passwordEncoder.encode(club.getPassword()));
        Club savedClub = clubService.createClub(club);
        return new ResponseEntity<>(new ApiResponse("Club registered successfully", savedClub), HttpStatus.CREATED);
    }

    public ResponseEntity<?> registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userService.createUser(user);
        return new ResponseEntity<>(new ApiResponse("User registered successfully", savedUser), HttpStatus.CREATED);
    }

    public ResponseEntity<?> isLogin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return new ResponseEntity<>(new ApiResponse("User is not logged in", null), HttpStatus.UNAUTHORIZED);
        }

        String token = Arrays.stream(cookies)
                .filter(cookie -> "token".equals(cookie.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);

        if (token == null) {
            return new ResponseEntity<>(new ApiResponse("User is not logged in", null), HttpStatus.UNAUTHORIZED);
        }

        return verifyToken(token);
    }

    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return new ResponseEntity<>(new ApiResponse("Logout successful", null), HttpStatus.OK);
    }

    public static class ApiResponse {
        private String message;
        private Object data;

        public ApiResponse(String message, Object data) {
            this.message = message;
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }
    }
}
