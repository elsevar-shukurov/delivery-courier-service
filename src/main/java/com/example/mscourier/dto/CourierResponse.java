package com.example.mscourier.dto;


import com.example.mscourier.enums.CourierStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourierResponse {
    private Long id;
    private String name;
    private CourierStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
