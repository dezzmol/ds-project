package com.digitalspirit.project.internship.entity;

import com.digitalspirit.project.intern.entity.Intern;
import com.digitalspirit.project.lessons.entity.Task;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "InternshipMembersTasks")
public class InternshipMembersTasks implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "internship_id", nullable = false, foreignKey = @ForeignKey(name = "internship_id"))
    private Internship internship;

    @Id
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "member_id"))
    private Intern intern;

    @Id
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false, foreignKey = @ForeignKey(name = "task_id"))
    private Task task;

    @Column(name = "status", nullable = false, length = 50)
    private String status;
}
