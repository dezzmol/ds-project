package com.digitalspirit.project.message.controller;

import com.digitalspirit.project.message.dto.CreateMessageDTO;
import com.digitalspirit.project.message.dto.MessageDTO;
import com.digitalspirit.project.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/")
    public ResponseEntity<List<MessageDTO>> getMessages() {
        return ResponseEntity.ok().body(messageService.getMessages());
    }
}
