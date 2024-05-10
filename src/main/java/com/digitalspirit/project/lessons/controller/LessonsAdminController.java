package com.digitalspirit.project.lessons.controller;

import com.digitalspirit.project.lessons.dto.CreateLessonDTO;
import com.digitalspirit.project.lessons.dto.LessonsDTO;
import com.digitalspirit.project.lessons.service.LessonsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/admin/lessons")
@RequiredArgsConstructor
@Transactional
public class LessonsAdminController {
    private final LessonsService lessonsService;

    @PostMapping("/")
    public ResponseEntity<LessonsDTO> createLesson(@RequestBody CreateLessonDTO lesson) {
        return ResponseEntity.ok(lessonsService.createLesson(lesson));
    }

    @PostMapping("/{lessonId}/internship/{internshipId}")
    public ResponseEntity<String> addLessonToInternship(@PathVariable Long lessonId, @PathVariable Long internshipId) {
        lessonsService.addLessonToInternship(lessonId, internshipId);
        return ResponseEntity.ok("lesson added to internship");
    }

    @PostMapping("/{lessonId}/internship/{internshipId}/open")
    public ResponseEntity<String> openLesson(@PathVariable Long lessonId, @PathVariable Long internshipId) {
        lessonsService.openLesson(lessonId, internshipId);
        return ResponseEntity.ok("lesson opened");
    }


}
