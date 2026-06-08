package com.example.mscourier.dao.entity;

import com.example.mscourier.enums.CourierStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import static com.example.mscourier.enums.CourierStatus.FREE;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "couriers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourierStatus status;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "courier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CourierProfile profile;
}
