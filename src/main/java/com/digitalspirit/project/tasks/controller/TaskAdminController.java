package com.digitalspirit.project.tasks.controller;

import com.digitalspirit.project.tasks.dto.CreateTaskDTO;
import com.digitalspirit.project.tasks.dto.TaskDTO;
import com.digitalspirit.project.tasks.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/tasks")
@RequiredArgsConstructor
@Api(value = "Task controller for admin", authorizations = {@Authorization(value = "basicAuth")})
public class TaskAdminController {
    private final TaskService taskService;

    @PostMapping("/")
    public ResponseEntity<TaskDTO> createTask(@RequestBody CreateTaskDTO task) {
        return ResponseEntity.ok(taskService.create(task));
    }
}
