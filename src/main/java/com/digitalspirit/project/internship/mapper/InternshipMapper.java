package com.digitalspirit.project.internship.mapper;

import com.digitalspirit.project.intern.dto.InternDTO;
import com.digitalspirit.project.internship.dto.InternshipApplicationsDTO;
import com.digitalspirit.project.internship.dto.InternshipDTO;
import com.digitalspirit.project.internship.dto.InternshipMembersDTO;
import com.digitalspirit.project.internship.entity.Internship;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InternshipMapper {
    InternshipDTO toInternshipDTO(Internship internship);

    InternshipApplicationsDTO toInternshipApplicationsDTO(InternshipDTO internshipDTO, List<InternDTO> applications);

    InternshipMembersDTO toInternshipMembersDTO(Internship internship, List<InternDTO> members);
}
