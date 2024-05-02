package com.digitalspirit.project.internship.dto;

import com.digitalspirit.project.intern.dto.InternDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class InternshipApplicationsDTO {
    private InternshipDTO internshipDTO;
    private List<InternDTO> applications;
}
