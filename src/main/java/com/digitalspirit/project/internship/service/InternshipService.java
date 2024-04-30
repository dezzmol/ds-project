package com.digitalspirit.project.internship.service;

import com.digitalspirit.project.intern.entity.Intern;
import com.digitalspirit.project.intern.service.InternService;
import com.digitalspirit.project.internship.dto.InternshipCreatingDTO;
import com.digitalspirit.project.internship.dto.InternshipDTO;
import com.digitalspirit.project.internship.entity.Internship;
import com.digitalspirit.project.internship.entity.InternshipApplications;
import com.digitalspirit.project.internship.mapper.InternshipMapper;
import com.digitalspirit.project.internship.repository.InternshipApplicationsRepository;
import com.digitalspirit.project.internship.repository.InternshipMemberRepository;
import com.digitalspirit.project.internship.repository.InternshipRepository;
import com.digitalspirit.project.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class InternshipService {
    private final InternshipRepository internshipRepository;
    private final InternshipMapper mapper;
    private final InternshipApplicationsRepository internshipApplicationsRepository;
    private final InternshipMemberRepository internshipMemberRepository;
    private final InternService internService;

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

        return mapper.toInternshipDTO(internshipRepository.save(internship));
    }

    public void registerOnInternship(Long internshipId) {
        Internship internship = internshipRepository.findById(internshipId)
                .orElseThrow(); //TODO: add exception

        if (LocalDateTime.now().isAfter(internship.getClosingDateForRegistration())) {
            throw new RuntimeException("Registration time has expired");
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        if (internshipMemberRepository.findByInternshipIdAndInternId(internshipId, user.getId()).isPresent()) {
            throw new RuntimeException("User is already participating in this internship");
        }

        Intern intern = internService.findByUserId(user.getId());

        if (internshipApplicationsRepository.findByInternId(intern.getId()).isPresent()) {
            throw new RuntimeException("User already registered on this Internship");
        }



        InternshipApplications application = InternshipApplications.builder()
                .internship(internship)
                .intern(intern)
                .build();

        internshipApplicationsRepository.save(application);
    }
}
