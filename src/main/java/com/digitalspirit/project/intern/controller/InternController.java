package com.digitalspirit.project.intern.controller;

import com.digitalspirit.project.intern.dto.InternRegisterDTO;
import com.digitalspirit.project.intern.service.InternService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/intern")
@RequiredArgsConstructor
public class InternController {
    private final InternService service;

    @PostMapping("/register")
    @ApiOperation(value = "Регистрация стажера")
    public ResponseEntity<?> register(@RequestBody InternRegisterDTO registerDTO) {
        service.save(registerDTO);
        return ResponseEntity.ok("Intern saved");
    }
}
