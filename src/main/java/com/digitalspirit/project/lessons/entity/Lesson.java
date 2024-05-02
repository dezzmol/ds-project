package com.digitalspirit.project.lessons.entity;

import com.digitalspirit.project.internship.entity.Internship;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "link_to_materials", nullable = false, length = 255)
    private String linkToMaterials;

    @ManyToMany
    @JoinTable(
            name = "InternshipLessons",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "internship_id")
    )
    private List<Internship> internships;
}
