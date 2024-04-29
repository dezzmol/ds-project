package com.digitalspirit.project.internship.repository;

import com.digitalspirit.project.internship.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternshipRepository extends JpaRepository<Internship, Long> {
}
