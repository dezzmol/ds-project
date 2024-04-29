package com.digitalspirit.project.internship.controller;

import com.digitalspirit.project.internship.dto.InternshipCreatingDTO;
import com.digitalspirit.project.internship.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/internship")
@RequiredArgsConstructor
public class InternshipAdminController {
    private final InternshipService service;

    @PostMapping("/create")
    public ResponseEntity<?> createInternship(@RequestBody InternshipCreatingDTO creatingDTO) {
        return ResponseEntity.ok(service.createInternship(creatingDTO));
    }
}
