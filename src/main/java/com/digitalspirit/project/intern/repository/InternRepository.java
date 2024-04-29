package com.digitalspirit.project.intern.repository;

import com.digitalspirit.project.intern.entity.Intern;
import com.digitalspirit.project.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InternRepository extends JpaRepository<Intern, Long> {
    @Query("FROM Intern WHERE user_id = ?1")
    Optional<Intern> findInternByUserId(Long id);
}
