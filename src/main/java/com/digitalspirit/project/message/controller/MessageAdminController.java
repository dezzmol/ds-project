package com.digitalspirit.project.message.controller;

import com.digitalspirit.project.message.dto.CreateMessageDTO;
import com.digitalspirit.project.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/message")
@RequiredArgsConstructor
public class MessageAdminController {
    private final MessageService messageService;

    @PostMapping("/")
    public ResponseEntity<String> createMessage(@RequestBody CreateMessageDTO createMessageDTO) {
        messageService.createMessageTo(createMessageDTO.getUserId(), createMessageDTO.getMessage());
        return ResponseEntity.ok("message created");
    }
}
