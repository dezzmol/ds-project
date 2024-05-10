package com.digitalspirit.project.message.dto;

import lombok.Data;

@Data
public class CreateMessageDTO {
    private Long userId;
    private String message;
}
