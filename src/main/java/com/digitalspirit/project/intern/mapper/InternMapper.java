package com.digitalspirit.project.intern.mapper;

import com.digitalspirit.project.intern.dto.InternDTO;
import com.digitalspirit.project.intern.entity.Intern;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InternMapper {
    InternDTO toInternDTO(Intern intern);
}
