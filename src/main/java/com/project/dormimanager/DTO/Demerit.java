package com.project.dormimanager.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class Demerit {
    private String studentId;
    private Date reveiveDate;
    private int reasonId;
    private int id;
}
