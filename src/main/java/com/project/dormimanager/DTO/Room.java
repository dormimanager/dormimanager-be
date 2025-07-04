package com.project.dormimanager.DTO;

import lombok.Data;

@Data
public class Room {
    private String gender;
    private int roomId;
    private int personLimit;
    private int buildingId;
    private int roomNumber;
}
