package com.digitalspirit.project.internship.controller;

import com.digitalspirit.project.internship.dto.InternshipCreatingDTO;
import com.digitalspirit.project.internship.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/internship")
@RequiredArgsConstructor
@Transactional
public class InternshipAdminController {
    private final InternshipService service;

    @PostMapping("/create")
    public ResponseEntity<?> createInternship(@RequestBody InternshipCreatingDTO creatingDTO) {
        return ResponseEntity.ok(service.createInternship(creatingDTO));
    }

    @GetMapping("/{internshipId}/applicants")
    public ResponseEntity<?> getInternshipApplications(@PathVariable Long internshipId) {
        return ResponseEntity.ok(service.getInternshipApplications(internshipId));
    }

    @PostMapping("/{internshipId}/acceptIntern/{internId}")
    public ResponseEntity<?> acceptIntern(@PathVariable Long internshipId, @PathVariable Long internId) {
        service.acceptIntern(internshipId, internId);
        return ResponseEntity.ok("Intern accepted");
    }

    @PostMapping("/{internshipId}/remove/{internId}")
    public ResponseEntity<?> removeIntern(@PathVariable Long internshipId, @PathVariable Long internId) {
        service.removeInternFromInternship(internshipId, internId);
        return ResponseEntity.ok("Intern removed");
    }

    @GetMapping("/{internshipId}/members")
    public ResponseEntity<?> getMembers(@PathVariable Long internshipId) {
        return ResponseEntity.ok(service.getMembers(internshipId));
    }
}
