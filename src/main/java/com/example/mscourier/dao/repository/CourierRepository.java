package com.example.mscourier.dao.repository;

import com.example.mscourier.dao.entity.Courier;
import com.example.mscourier.enums.CourierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    List<Courier> findByStatus(CourierStatus status);
}
