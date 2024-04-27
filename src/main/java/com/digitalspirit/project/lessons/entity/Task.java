package com.digitalspirit.project.lessons.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "link_to_repository", nullable = false, length = 255)
    private String linkToRepository;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false, foreignKey = @ForeignKey(name = "lesson_id"))
    private Lesson lesson;
}
