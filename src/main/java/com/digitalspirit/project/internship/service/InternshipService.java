package com.digitalspirit.project.internship.service;

import com.digitalspirit.project.internship.dto.InternshipCreatingDTO;
import com.digitalspirit.project.internship.dto.InternshipDTO;
import com.digitalspirit.project.internship.entity.Internship;
import com.digitalspirit.project.internship.mapper.InternshipMapper;
import com.digitalspirit.project.internship.repository.InternshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class InternshipService {
    private final InternshipRepository repository;
    private final InternshipMapper mapper;

    public InternshipDTO createInternship(InternshipCreatingDTO creatingDTO) {
        if (creatingDTO.getStartDate().isAfter(creatingDTO.getEndDate())) {
            throw new RuntimeException("Start date should be after end date"); //TODO: add exception
        }
        if (creatingDTO.getClosingDateForRegistration().isBefore(creatingDTO.getStartDate())) {
            throw new RuntimeException("Closing date for registration should be after start date"); //TODO: add exception
        }
        if (creatingDTO.getClosingDateForRegistration().isAfter(creatingDTO.getEndDate())) {
            throw new RuntimeException("Closing date for registration should be before end date"); //TODO: add exception
        }

        Internship internship = Internship.builder()
                .name(creatingDTO.getName())
                .description(creatingDTO.getDescription())
                .startDate(creatingDTO.getStartDate())
                .endDate(creatingDTO.getEndDate())
                .closingDateForRegistration(creatingDTO.getClosingDateForRegistration())
                .status(creatingDTO.getStatus())
                .lessons(new ArrayList<>())
                .build();

        return mapper.toInternshipDTO(repository.save(internship));
    }
}
