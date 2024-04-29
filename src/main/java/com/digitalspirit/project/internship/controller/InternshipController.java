package com.digitalspirit.project.internship.controller;

import com.digitalspirit.project.internship.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class InternshipController {
    private final InternshipService service;

}
