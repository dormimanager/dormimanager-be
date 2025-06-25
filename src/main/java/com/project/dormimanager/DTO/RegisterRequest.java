package com.project.dormimanager.DTO;

import lombok.Data;

@Data
public class RegisterRequest {
    private String studentId;
    private String password;
    private String passwordConfirm;
    private String name;
    private String phone;
    private String gender;
    private String imgUrl;
    private String email;
    // getters, setters
}

