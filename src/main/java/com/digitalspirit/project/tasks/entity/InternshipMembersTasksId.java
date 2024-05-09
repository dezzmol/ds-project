package com.digitalspirit.project.tasks.entity;

import com.digitalspirit.project.intern.entity.Intern;
import com.digitalspirit.project.internship.entity.Internship;

import java.io.Serializable;

public class InternshipMembersTasksId implements Serializable {
    private Internship internship;
    private Intern intern;
    private Task task;
}
