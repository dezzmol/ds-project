package com.digitalspirit.project.internship.controller;

import com.digitalspirit.project.internship.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/internship")
@RequiredArgsConstructor
public class InternshipController {
    private final InternshipService service;

    @PostMapping("/{internshipId}/register")
    public ResponseEntity<?> registerOnInternship(@PathVariable Long internshipId) {
        service.registerOnInternship(internshipId);
        return ResponseEntity.ok("Intern registered on internship successfully");
    }
}
