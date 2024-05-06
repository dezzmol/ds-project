package com.digitalspirit.project.tasks.entity;

import com.digitalspirit.project.lessons.entity.Lessons;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@Table(name = "Tasks")
@AllArgsConstructor
@RequiredArgsConstructor
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "link_to_repository", nullable = false, length = 255)
    private String linkToRepository;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false, foreignKey = @ForeignKey(name = "lesson_id"))
    private Lessons lessons;
}
