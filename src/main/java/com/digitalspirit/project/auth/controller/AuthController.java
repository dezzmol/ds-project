package com.digitalspirit.project.auth.controller;

import com.digitalspirit.project.auth.service.AuthService;
import com.digitalspirit.project.user.dto.SignUpDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDTO) {
        authService.signUp(signUpDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body("Welcome");
    }
}