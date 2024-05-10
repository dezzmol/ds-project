package com.digitalspirit.project.user.service;

import com.digitalspirit.project.exceptions.BadRequestException;
import com.digitalspirit.project.user.Role;
import com.digitalspirit.project.user.User;
import com.digitalspirit.project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> getAdmins() {
        return userRepository.findAllByRole(Role.ROLE_ADMIN)
                .orElseThrow(() -> new BadRequestException(HttpStatus.NOT_FOUND, "Admins not found"));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BadRequestException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
