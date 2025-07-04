package com.project.dormimanager.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class restaurantPayment {
    private int id;
    private String studentId;
    private Date paymentDate;
    private int amount;
}
