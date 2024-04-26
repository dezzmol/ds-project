package com.digitalspirit.project.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body("Welcome admin");
    }
}
