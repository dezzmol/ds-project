package com.digitalspirit.project.lessons.controller;

import com.digitalspirit.project.lessons.dto.LessonsDTO;
import com.digitalspirit.project.lessons.service.LessonsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/user/lessons")
@RequiredArgsConstructor
@Transactional
public class LessonsController {
    private final LessonsService lessonsService;

    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonsDTO> getLesson(@PathVariable Long lessonId) {
        return ResponseEntity.ok(lessonsService.getLessons(lessonId));
    }
}
