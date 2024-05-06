package com.digitalspirit.project.lessons.repository;

import com.digitalspirit.project.internship.entity.Internship;
import com.digitalspirit.project.lessons.entity.InternshipLesson;
import com.digitalspirit.project.lessons.entity.Lessons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InternshipLessonsRepository extends JpaRepository<InternshipLesson, Long> {
    Optional<InternshipLesson> findByInternshipAndLesson(Internship internship, Lessons lesson);
}
