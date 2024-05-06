package com.digitalspirit.project.tasks.mapper;

import com.digitalspirit.project.tasks.dto.TaskDTO;
import com.digitalspirit.project.tasks.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO toTaskDTO(Task task);
}
