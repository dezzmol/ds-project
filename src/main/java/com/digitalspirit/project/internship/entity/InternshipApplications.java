package com.digitalspirit.project.internship.entity;

import com.digitalspirit.project.intern.entity.Intern;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@Table(name = "InternshipApplications")
@IdClass(InternshipApplicationId.class)
public class InternshipApplications implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "internship_id", nullable = false, foreignKey = @ForeignKey(name = "internship_id"))
    private Internship internship;

    @Id
    @ManyToOne
    @JoinColumn(name = "intern_id", nullable = false, foreignKey = @ForeignKey(name = "intern_id"))
    private Intern intern;
}
