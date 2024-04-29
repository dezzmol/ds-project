package com.digitalspirit.project.intern.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class InternRegisterDTO implements Serializable {
    private String fullname;
    private String phone;
    private String telegramId;
    private String info;
    private Date dateOfBirth;
    private String city;
    private String educationStatus;
    private String university;
    private String faculty;
    private String speciality;
    private Integer course;
}
