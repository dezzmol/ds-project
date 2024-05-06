package com.digitalspirit.project.internship.dto;

import com.digitalspirit.project.intern.dto.InternDTO;
import lombok.Data;

import java.util.List;

@Data
public class InternshipMembersDTO {
    private InternshipDTO internshipDTO;
    private List<InternDTO> members;
}
