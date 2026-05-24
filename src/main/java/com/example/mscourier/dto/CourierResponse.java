package com.example.mscourier.dto;


import com.example.mscourier.enums.CourierStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(NON_NULL)
public class CourierResponse {
    private Long id;
    private String name;
    private CourierStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
