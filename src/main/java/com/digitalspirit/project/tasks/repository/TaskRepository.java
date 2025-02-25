package com.digitalspirit.project.tasks.repository;

import com.digitalspirit.project.lessons.entity.Lessons;
import com.digitalspirit.project.tasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<List<Task>> findByLessons(Lessons lesson);
}
