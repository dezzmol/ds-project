package com.digitalspirit.project.lessons.mapper;

import com.digitalspirit.project.lessons.dto.LessonsDTO;
import com.digitalspirit.project.lessons.entity.Lessons;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonsMapper {
    LessonsDTO toLessonDTO(Lessons lessons);
}
