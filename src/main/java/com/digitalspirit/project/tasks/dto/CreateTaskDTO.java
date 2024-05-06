package com.digitalspirit.project.tasks.dto;

import lombok.Data;

@Data
public class CreateTaskDTO {
    private String name;
    private String linkToRepository;
    private Long lesson_id;
}
