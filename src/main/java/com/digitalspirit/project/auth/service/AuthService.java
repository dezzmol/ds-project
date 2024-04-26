package com.digitalspirit.project.auth.service;

import com.digitalspirit.project.user.Role;
import com.digitalspirit.project.user.User;
import com.digitalspirit.project.user.dto.SignUpDTO;
import com.digitalspirit.project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpDTO signUpDTO) {
        if (userService.existsByUsername(signUpDTO.getUsername())) {
            throw new RuntimeException("user already exist");
        }

        User user = User.builder()
                .username(signUpDTO.getUsername())
                .email(signUpDTO.getEmail())
                .password(passwordEncoder.encode(signUpDTO.getPassword()))
                .role(Role.ROLE_INTERN)
                .build();

        userService.save(user);
    }
}
