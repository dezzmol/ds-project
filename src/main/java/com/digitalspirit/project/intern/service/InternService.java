package com.digitalspirit.project.intern.service;

import com.digitalspirit.project.intern.dto.InternRegisterDTO;
import com.digitalspirit.project.intern.entity.Intern;
import com.digitalspirit.project.intern.repository.InternRepository;
import com.digitalspirit.project.user.User;
import com.digitalspirit.project.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InternService {
    private final InternRepository repository;
    public void save(InternRegisterDTO registerDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Intern intern;

        if (repository.findInternByUserId(user.getId()).isPresent()) {
            intern = repository.findInternByUserId(user.getId())
                    .orElseThrow(); //TODO: add exception
            user = intern.getUser();
        } else {
            intern = Intern.builder()
                    .build();
        }

        updateInternFields(intern, registerDTO, user);
        repository.save(intern);
    }

    private void updateInternFields(Intern intern, InternRegisterDTO registerDTO, User user) {
        intern.setUser(user);
        intern.setFullname(registerDTO.getFullname());
        intern.setPhone(registerDTO.getPhone());
        intern.setTelegramId(registerDTO.getTelegramId());
        intern.setInfo(registerDTO.getInfo());
        intern.setDateOfBirth(registerDTO.getDateOfBirth());
        intern.setCity(registerDTO.getCity());
        intern.setEducationStatus(registerDTO.getEducationStatus());
        intern.setUniversity(registerDTO.getUniversity());
        intern.setFaculty(registerDTO.getFaculty());
        intern.setSpeciality(registerDTO.getSpeciality());
        intern.setCourse(registerDTO.getCourse());
    }

}
