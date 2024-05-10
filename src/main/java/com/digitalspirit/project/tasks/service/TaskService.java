package com.digitalspirit.project.tasks.service;

import com.digitalspirit.project.config.GitlabApiConfig;
import com.digitalspirit.project.exceptions.BadRequestException;
import com.digitalspirit.project.intern.entity.Intern;
import com.digitalspirit.project.internship.entity.Internship;
import com.digitalspirit.project.lessons.entity.Lessons;
import com.digitalspirit.project.lessons.repository.LessonsRepository;
import com.digitalspirit.project.message.service.MessageService;
import com.digitalspirit.project.tasks.dto.CreateTaskDTO;
import com.digitalspirit.project.tasks.dto.TaskDTO;
import com.digitalspirit.project.tasks.entity.InternshipMembersTasks;
import com.digitalspirit.project.tasks.entity.Task;
import com.digitalspirit.project.tasks.enums.InternshipMembersTasksStatus;
import com.digitalspirit.project.tasks.mapper.TaskMapper;
import com.digitalspirit.project.tasks.repository.InternshipMemberTasksRepository;
import com.digitalspirit.project.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final LessonsRepository lessonsRepository;
    private final TaskMapper taskMapper;
    private final InternshipMemberTasksRepository internshipMemberTasksRepository;
    private final MessageService messageService;

    public TaskDTO create(CreateTaskDTO taskDTO) {
        Lessons lessons = lessonsRepository.findById(taskDTO.getLesson_id())
                .orElseThrow(() -> new BadRequestException(HttpStatus.NOT_FOUND, "Lesson not found"));

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

    public List<TaskDTO> getTasksDTO(Long lessonId) {
        Lessons lessons = lessonsRepository.findById(lessonId)
                .orElseThrow(() -> new BadRequestException(HttpStatus.NOT_FOUND, "Lesson not found"));

        List<Task> tasks = taskRepository.findByLessons(lessons)
                .orElseThrow(() -> new BadRequestException(HttpStatus.NOT_FOUND, "Tasks not found"));

        List<TaskDTO> taskDTOS = new ArrayList<>();
        tasks.forEach(task -> {
            taskDTOS.add(taskMapper.toTaskDTO(task));
        });

        return taskDTOS;
    }

    public List<Task> getTasks(Long lessonId) {
        Lessons lessons = lessonsRepository.findById(lessonId)
                .orElseThrow(() -> new BadRequestException(HttpStatus.NOT_FOUND, "Lesson not found"));

        return taskRepository.findByLessons(lessons)
                .orElseThrow(() -> new BadRequestException(HttpStatus.NOT_FOUND, "Tasks not found"));
    }

    public void openTasks(Internship internship, Long lessonId, List<Intern> interns) {
        List<Task> tasks = getTasks(lessonId);
        //TODO: Доработать решение за время меньшее чем O(tasksCount * internsCount)
        for (Intern intern : interns) {
            for (Task task : tasks) {
                InternshipMembersTasks internshipMembersTasks = InternshipMembersTasks.builder()
                        .internship(internship)
                        .intern(intern)
                        .task(task)
                        .status(InternshipMembersTasksStatus.NOT_RESOLVED.name())
                        .build();

                forkProject(task, intern);
                internshipMemberTasksRepository.save(internshipMembersTasks);
            }
        }
    }

    public void forkProject(Task task, Intern intern) {
        try {
            GitLabApi gitLabApi = GitlabApiConfig.gitlabApi();
            String projectUrl = task.getLinkToRepository();
            String username = intern.getUser().getUsername();

            User user = gitLabApi.getUserApi().getUser(username);
            if (user == null) {
                messageService.createMessageForAdmins("User not found");
                throw new GitLabApiException("User not found");
            }

            Project project = gitLabApi.getProjectApi().getProject(projectUrl);
            if (project == null) {
                messageService.createMessageForAdmins("Project not found");
                throw new GitLabApiException("Project not found");
            }

            Project forkedProject = gitLabApi.getProjectApi().forkProject((Object) project.getId(), (String) null);

        } catch (GitLabApiException e) {
            messageService.createMessageForAdmins("Error while forking: " + e.getMessage());
            ;
        }
    }
}
