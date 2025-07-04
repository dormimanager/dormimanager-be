package com.project.dormimanager.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class restaurant {
    private int id;
    private String studentId;
    private Date startDate;
    private Date endDate;
    private int choice;
    private int count;
}
