package com.digitalspirit.project.tasks.entity;

import com.digitalspirit.project.intern.entity.Intern;
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
@Table(name = "InternshipMembersTasks")
@IdClass(InternshipMembersTasksId.class)
@AllArgsConstructor
@NoArgsConstructor
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
