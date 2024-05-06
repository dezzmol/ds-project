package com.digitalspirit.project.lessons.entity;

import com.digitalspirit.project.internship.entity.Internship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@Table(name = "InternshipLessons")
@IdClass(InternshipLessonId.class)
@NoArgsConstructor
@AllArgsConstructor
public class InternshipLesson implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "internship_id", nullable = false, foreignKey = @ForeignKey(name = "internship_id"))
    private Internship internship;

    @Id
    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false, foreignKey = @ForeignKey(name = "lesson_id"))
    private Lessons lesson;

    @Column(name = "is_open", nullable = false)
    @Builder.Default
    private Boolean isOpen = false;
}