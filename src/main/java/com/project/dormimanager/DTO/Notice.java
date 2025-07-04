package com.project.dormimanager.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class Notice {
    private String studentId;
    private String category;
    private String title;
    private String content;
    private Date regDate;
    private Long views;
    private Long id;
    private String name;
    // getters, setters
}