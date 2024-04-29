package com.digitalspirit.project.internship.mapper;

import com.digitalspirit.project.internship.dto.InternshipDTO;
import com.digitalspirit.project.internship.entity.Internship;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InternshipMapper {
    InternshipDTO toInternshipDTO(Internship internship);
}
