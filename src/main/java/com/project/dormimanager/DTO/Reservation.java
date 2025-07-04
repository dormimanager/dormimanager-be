package com.project.dormimanager.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class Reservation {
    private String studentId;
    private String reservType;
    private Integer laundryId;
    private Integer studyroomId;
    private Date reservTime;
    private int buildingId;
    private int id;
}
