package com.digitalspirit.project.internship.dto;

import com.digitalspirit.project.lessons.dto.LessonsDTO;
import com.digitalspirit.project.lessons.entity.Lessons;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InternshipDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime closingDateForRegistration;
    private String status;
    private List<LessonsDTO> lessons;
}
