package com.project.dormimanager.DTO;

import lombok.Data;

@Data
public class Member {
    private Long studentId;
    private String password;
    private String role;
    private String name;
    private String phone;
    private String  gender;
    private Long id;
    private String imgUrl;
    private String email;
    private String major;
    // getters, setters
}

