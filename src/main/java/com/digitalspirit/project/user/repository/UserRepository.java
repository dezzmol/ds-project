package com.digitalspirit.project.user.repository;

import com.digitalspirit.project.user.Role;
import com.digitalspirit.project.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<List<User>> findAllByRole(Role role);
}