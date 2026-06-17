package com.example.mscourier.dto;

import com.example.mscourier.enums.VehicleType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourierCreateRequestDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    private String surname;

    @NotBlank(message = "Phone cannot be blank")
    @Pattern(regexp = "^\\d{8,15}$",
            message = "Phone must contain only digits and be 8-15 characters long")
    private String phoneNumber;


    private VehicleType vehicleType;

    @Pattern(regexp = "^\\d{2}-[A-Z]{2}-\\d{3}$",
            message = "License plate must follow format XX-YY-XXX (X=digit, Y=uppercase letter)")
    private String licensePlate;
}