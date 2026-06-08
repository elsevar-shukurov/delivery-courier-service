package com.example.mscourier.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "courier_profile")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourierProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id", nullable = false, unique = true)
    private Courier courier;

    @NotBlank(message = "Name cannot be blank")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Column(nullable = false)
    private String surname;

    @NotBlank(message = "Phone cannot be blank")
    @Column(nullable = false)
    private String phone;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "license_plate")
    private String licensePlate;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
