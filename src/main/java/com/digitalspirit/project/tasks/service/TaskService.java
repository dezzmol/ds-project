package com.digitalspirit.project.tasks.service;

import com.digitalspirit.project.exceptions.BadRequestException;
import com.digitalspirit.project.lessons.entity.Lessons;
import com.digitalspirit.project.lessons.service.LessonsService;
import com.digitalspirit.project.tasks.dto.CreateTaskDTO;
import com.digitalspirit.project.tasks.dto.TaskDTO;
import com.digitalspirit.project.tasks.entity.Task;
import com.digitalspirit.project.tasks.mapper.TaskMapper;
import com.digitalspirit.project.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final LessonsService lessonsService;
    private final TaskMapper taskMapper;

    public TaskDTO create(CreateTaskDTO taskDTO) {
        Lessons lessons = lessonsService.findLessonById(taskDTO.getLesson_id());

        Task task = Task.builder()
                .name(taskDTO.getName())
                .linkToRepository(taskDTO.getLinkToRepository())
                .lessons(lessons)
                .build();

        return taskMapper.toTaskDTO(taskRepository.save(task));
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(HttpStatus.NOT_FOUND, "Task not found"));
    }

    public List<TaskDTO> getTasks(Long lessonId) {
        Lessons lessons = lessonsService.findLessonById(lessonId);

        List<Task> tasks = taskRepository.findByLessons(lessons)
                .orElseThrow(() -> new BadRequestException(HttpStatus.NOT_FOUND, "Tasks not found"));

        List<TaskDTO> taskDTOS = new ArrayList<>();
        tasks.forEach(task -> {
            taskDTOS.add(taskMapper.toTaskDTO(task));
        });

        return taskDTOS;
    }
}
