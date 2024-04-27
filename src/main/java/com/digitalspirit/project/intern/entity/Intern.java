package com.digitalspirit.project.intern.entity;

import com.digitalspirit.project.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "intern")
public class Intern {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @Column(name = "fullname", nullable = false, length = 150)
    private String fullname;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "telegram_id", nullable = false, length = 50)
    private String telegramId;

    @Column(name = "info", nullable = false, columnDefinition = "TEXT")
    private String info;

    @Column(name = "date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "education_status", nullable = false, length = 100)
    private String educationStatus;

    @Column(name = "university", nullable = false, length = 200)
    private String university;

    @Column(name = "faculty", nullable = false, length = 200)
    private String faculty;

    @Column(name = "speciality", nullable = false, length = 150)
    private String speciality;

    @Column(name = "course", nullable = false)
    private Integer course;
}
