package com.example.mscourier.mapper;

import com.example.mscourier.dao.entity.Courier;
import com.example.mscourier.dto.CourierCreateRequest;
import com.example.mscourier.dto.CourierResponse;
import com.example.mscourier.enums.CourierStatus;

public class CourierMapper {
    public static Courier toEntity(CourierCreateRequest request) {
        return Courier.builder()
                .name(request.getName())
                .status(CourierStatus.FREE)
                .build();
    }

    public static CourierResponse toResponse(Courier courier) {
        return CourierResponse.builder()
                .id(courier.getId())
                .name(courier.getName())
                .status(courier.getStatus())
                .createdAt(courier.getCreatedAt())
                .updatedAt(courier.getUpdatedAt())
                .build();
    }
}
