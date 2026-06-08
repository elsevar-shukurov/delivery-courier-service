package com.example.mscourier.dao.repository;

import com.example.mscourier.dao.entity.CourierProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourierProfileRepository extends JpaRepository<CourierProfile, Long> {
    Optional<CourierProfile> findByCourierId(Long courierId);
}
