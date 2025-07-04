package com.project.dormimanager.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class Complain {
    private String studentId;
    private String category;
    private String title;
    private String content;
    private Date regDate;
    private Long id;
    private String name;
    private String publics;
    private String state;
    // getters, setters
}