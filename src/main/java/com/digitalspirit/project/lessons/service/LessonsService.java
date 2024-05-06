package com.digitalspirit.project.lessons.service;

import com.digitalspirit.project.exceptions.BadRequestException;
import com.digitalspirit.project.internship.entity.Internship;
import com.digitalspirit.project.internship.service.InternshipService;
import com.digitalspirit.project.lessons.dto.CreateLessonDTO;
import com.digitalspirit.project.lessons.dto.LessonsDTO;
import com.digitalspirit.project.lessons.entity.InternshipLesson;
import com.digitalspirit.project.lessons.entity.Lessons;
import com.digitalspirit.project.lessons.mapper.LessonsMapper;
import com.digitalspirit.project.lessons.repository.InternshipLessonsRepository;
import com.digitalspirit.project.lessons.repository.LessonsRepository;
import com.digitalspirit.project.tasks.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonsService {
    private final LessonsRepository lessonsRepository;
    private final InternshipLessonsRepository internshipLessonsRepository;
    private final InternshipService internshipService;
    private final LessonsMapper mapper;

    public LessonsDTO createLesson(CreateLessonDTO lessonDTO) {
        Lessons lessons = Lessons.builder()
                .name(lessonDTO.getName())
                .linkToMaterials(lessonDTO.getLinkToMaterials())
                .build();

        return mapper.toLessonDTO(lessonsRepository.save(lessons));
    }

    public Lessons findLessonById(Long id) {
        return lessonsRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(HttpStatus.NOT_FOUND, "Lesson not found"));
    }

    public LessonsDTO getLessons(Long id) {
        return mapper.toLessonDTO(findLessonById(id));
    }

    public void addLessonToInternship(Long lessonId, Long internshipId) {
        Lessons lesson = findLessonById(lessonId);
        Internship internship = internshipService.findInternshipById(internshipId);

        InternshipLesson internshipLessons = InternshipLesson.builder()
                .lesson(lesson)
                .internship(internship)
                .build();

        internshipLessonsRepository.save(internshipLessons);
    }

    public void openLesson(Long lessonId, Long internshipId) {
        Lessons lesson = findLessonById(lessonId);
        Internship internship = internshipService.findInternshipById(internshipId);

        InternshipLesson internshipLesson = internshipLessonsRepository.findByInternshipAndLesson(internship, lesson)
                .orElseThrow(() -> new BadRequestException(HttpStatus.NOT_FOUND, "Lesson not found"));

        internshipLesson.setIsOpen(true);
        internshipLessonsRepository.save(internshipLesson);
    }
}
