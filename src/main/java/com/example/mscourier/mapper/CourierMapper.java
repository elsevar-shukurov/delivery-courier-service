package com.example.mscourier.mapper;

import com.example.mscourier.dao.entity.Courier;
import com.example.mscourier.dao.entity.CourierProfile;
import com.example.mscourier.dto.CourierCreateRequestDto;
import com.example.mscourier.dto.CourierResponseDto;
import com.example.mscourier.enums.CourierStatus;

public class CourierMapper {

    public static Courier createCourierEntity() {
        return Courier.builder()
                .status(CourierStatus.FREE)
                .build();
    }

    public static CourierProfile toProfileEntity(CourierCreateRequestDto request) {
        return CourierProfile.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .phone(request.getPhone())
                .vehicleType(request.getVehicleType())
                .licensePlate(request.getLicensePlate())
                .build();
    }

    public static CourierResponseDto toResponse(Courier courier) {
        CourierProfile profile = courier.getProfile();
        if (profile == null) {
            throw new IllegalStateException("Courier profile not found for id: " + courier.getId());
        }
        return CourierResponseDto.builder()
                .id(courier.getId())
                .status(courier.getStatus())
                .name(profile.getName())
                .surname(profile.getSurname())
                .phone(profile.getPhone())
                .vehicleType(profile.getVehicleType())
                .licensePlate(profile.getLicensePlate())
                .createdAt(courier.getCreatedAt())
                .updatedAt(courier.getUpdatedAt())
                .build();
    }
}