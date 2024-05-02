package com.digitalspirit.project.internship.dto;

import com.digitalspirit.project.intern.dto.InternDTO;
import com.digitalspirit.project.internship.entity.Internship;
import lombok.Data;

import java.util.List;

@Data
public class InternshipMembersDTO {
    private Internship internship;
    private List<InternDTO> members;
}
