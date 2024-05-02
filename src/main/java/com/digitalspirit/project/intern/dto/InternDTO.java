package com.digitalspirit.project.intern.dto;

import com.digitalspirit.project.user.dto.UserDTO;
import lombok.Data;

import java.util.Date;

@Data
public class InternDTO {
    private Long id;
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
    private UserDTO user;
}
