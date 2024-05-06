package com.digitalspirit.project.tasks.controller;

import com.digitalspirit.project.tasks.dto.TaskDTO;
import com.digitalspirit.project.tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/lessons/{lessonId}")
    public ResponseEntity<List<TaskDTO>> getTasks(@PathVariable Long lessonId) {
        return ResponseEntity.ok(taskService.getTasks(lessonId));
    }
}
