package com.project.dormimanager.DTO;

import lombok.Data;
import java.util.Date;

@Data
public class Delivery {
    private int id;
    private String studentId;
    private Date registTime;
    private Date receiveTime;
    private String deliveryCompany;
    private String phone;
    private String name;
    private String deliveryNumber;
}
