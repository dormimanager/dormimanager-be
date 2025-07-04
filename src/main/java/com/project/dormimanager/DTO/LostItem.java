package com.project.dormimanager.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class LostItem {
    private String itemName;
    private String category;
    private String description;
    private Date foundDate;
    private String foundLocation;
    private String state;
    private String imageUrl;
    private String studentId;
    private int id;
    private String receiverStudentId;
    private Date receiveDate;
}
