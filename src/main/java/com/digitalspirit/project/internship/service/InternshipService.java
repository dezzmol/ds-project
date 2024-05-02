package com.digitalspirit.project.internship.service;

import com.digitalspirit.project.exceptions.BadRequestException;
import com.digitalspirit.project.intern.dto.InternDTO;
import com.digitalspirit.project.intern.entity.Intern;
import com.digitalspirit.project.intern.service.InternService;
import com.digitalspirit.project.internship.dto.InternshipApplicationsDTO;
import com.digitalspirit.project.internship.dto.InternshipCreatingDTO;
import com.digitalspirit.project.internship.dto.InternshipDTO;
import com.digitalspirit.project.internship.dto.InternshipMembersDTO;
import com.digitalspirit.project.internship.entity.Internship;
import com.digitalspirit.project.internship.entity.InternshipApplications;
import com.digitalspirit.project.internship.entity.InternshipMember;
import com.digitalspirit.project.internship.enums.InternshipMemberStatus;
import com.digitalspirit.project.internship.mapper.InternshipMapper;
import com.digitalspirit.project.internship.repository.InternshipApplicationsRepository;
import com.digitalspirit.project.internship.repository.InternshipMemberRepository;
import com.digitalspirit.project.internship.repository.InternshipRepository;
import com.digitalspirit.project.user.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InternshipService {
    private final InternshipRepository internshipRepository;
    private final InternshipMapper mapper;
    private final InternshipApplicationsRepository internshipApplicationsRepository;
    private final InternshipMemberRepository internshipMemberRepository;
    private final InternService internService;

    public Internship findInternshipById(Long id) {
        return internshipRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(HttpStatus.NOT_FOUND, "Internship not found"));
    }

    public InternshipDTO createInternship(InternshipCreatingDTO creatingDTO) {
        if (creatingDTO.getStartDate().isAfter(creatingDTO.getEndDate())) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Start date should be after end date");
        }
        if (creatingDTO.getClosingDateForRegistration().isBefore(creatingDTO.getStartDate())) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Closing date for registration should be after start date");
        }
        if (creatingDTO.getClosingDateForRegistration().isAfter(creatingDTO.getEndDate())) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Closing date for registration should be before end date");
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
        Internship internship = findInternshipById(internshipId);

        if (LocalDateTime.now().isAfter(internship.getClosingDateForRegistration())) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Registration time has expired");
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        Intern intern = internService.findByUserId(user.getId());

        if (internshipMemberRepository.findByInternshipIdAndInternId(internshipId, intern.getId()).isPresent()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Intern is already participating for this internship");
        }

        if (internshipApplicationsRepository.findByInternshipIdAndInternId(internship.getId(), intern.getId()).isPresent()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Intern already registered for this Internship");
        }

        InternshipApplications application = InternshipApplications.builder()
                .internship(internship)
                .intern(intern)
                .build();

        internshipApplicationsRepository.save(application);
    }

    public InternshipApplicationsDTO getInternshipApplications(Long internshipId) {
        Internship internship = findInternshipById(internshipId);

        List<InternshipApplications> applications = internshipApplicationsRepository.findByInternshipId(internshipId)
                .orElseThrow(() -> new BadRequestException(HttpStatus.NOT_FOUND, "Internship applications not found"));

        InternshipDTO internshipDTO = mapper.toInternshipDTO(internship);
        List<InternDTO> interns = applications.stream()
                .map(application -> {
                    Intern intern = application.getIntern();
                    return internService.toInternDTO(intern);
                })
                .collect(Collectors.toList());

        return mapper.toInternshipApplicationsDTO(internshipDTO, interns);
    }

    public void acceptIntern(Long internshipId, Long internId) {
        Intern intern = internService.findById(internId);
        Internship internship = findInternshipById(internshipId);

        if (internshipMemberRepository.findByInternshipIdAndInternId(internshipId, internId).isPresent()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Intern is already participating for this internship");
        }

        if (internshipApplicationsRepository.findByInternshipIdAndInternId(internship.getId(), intern.getId()).isEmpty()) {
            throw new BadRequestException(HttpStatus.NOT_FOUND, "Intern is not registered for this Internship");
        }

        InternshipMember member = InternshipMember.builder()
                .intern(intern)
                .internship(internship)
                .status(InternshipMemberStatus.ACTIVE.name())
                .build();

        internshipMemberRepository.save(member);
    }

    public void removeInternFromInternship(Long internshipId, Long internId) {
        Intern intern = internService.findById(internId);
        Internship internship = findInternshipById(internshipId);

        InternshipMember member = internshipMemberRepository.findByInternshipIdAndInternId(internshipId, internId)
                .orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "Intern is not participating for this internship"));

        if (Objects.equals(member.getStatus(), InternshipMemberStatus.KICKED.name())) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Intern is already kicked from this internship");
        }

        member.setStatus(InternshipMemberStatus.KICKED.name());
        internshipMemberRepository.save(member);
    }

    public InternshipMembersDTO getMembers( Long internshipId) {
        Internship internship = findInternshipById(internshipId);

        List<InternshipMember> members = internshipMemberRepository.findByInternshipId(internshipId)
                .orElseThrow(() -> new BadRequestException(HttpStatus.NOT_FOUND, "Internship members not found"));

        List<InternDTO> interns = members.stream()
                .map(member -> {
                    Intern intern = member.getIntern();
                    return internService.toInternDTO(intern);
                })
                .collect(Collectors.toList());

        return mapper.toInternshipMembersDTO(internship, interns);
    }
}
