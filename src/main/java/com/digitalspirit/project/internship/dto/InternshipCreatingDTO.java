package com.digitalspirit.project.internship.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InternshipCreatingDTO {
    private String name;
    private String description;
    @JsonFormat(locale = "ru", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;
    @JsonFormat(locale = "ru", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;
    @JsonFormat(locale = "ru", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime closingDateForRegistration;
    private String status;
}
