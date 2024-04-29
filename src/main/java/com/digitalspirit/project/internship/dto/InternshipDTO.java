package com.digitalspirit.project.internship.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InternshipDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime closingDateForRegistration;
    private String status;
}
