package com.digitalspirit.project.lessons.repository;

import com.digitalspirit.project.lessons.entity.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonsRepository extends JpaRepository<Lessons, Long> {
}
