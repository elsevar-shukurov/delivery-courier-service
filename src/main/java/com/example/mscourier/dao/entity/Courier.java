package com.example.mscourier.dao.entity;

import com.example.mscourier.enums.CourierStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static com.example.mscourier.enums.CourierStatus.FREE;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="couriers")
public class Courier {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @Builder.Default
    @Enumerated(STRING)
    private CourierStatus status = FREE;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
