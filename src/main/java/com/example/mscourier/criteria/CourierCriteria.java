package com.example.mscourier.criteria;

import com.example.mscourier.enums.CourierStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourierCriteria {
    private CourierStatus status;
    private String name;
    private String surname;
    private String phone;
    private String vehicleType;
    private String licensePlate;
    private LocalDateTime minCreatedAt;
    private LocalDateTime maxCreatedAt;
}
