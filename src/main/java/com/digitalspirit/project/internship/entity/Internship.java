package com.digitalspirit.project.internship.entity;

import com.digitalspirit.project.lessons.entity.Lessons;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "internship")
@AllArgsConstructor
@RequiredArgsConstructor
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "closing_date_for_registration", nullable = false)
    private LocalDateTime closingDateForRegistration;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @ManyToMany
    @JoinTable(
            name = "InternshipLessons",
            joinColumns = @JoinColumn(name = "internship_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    private List<Lessons> lessons;
}
