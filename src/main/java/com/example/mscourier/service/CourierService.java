package com.example.mscourier.service;

import com.example.mscourier.dao.entity.Courier;
import com.example.mscourier.dao.repository.CourierRepository;
import com.example.mscourier.dto.CourierCreateRequest;
import com.example.mscourier.dto.CourierResponse;
import com.example.mscourier.mapper.CourierMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mscourier.enums.CourierStatus.FREE;
import static com.example.mscourier.mapper.CourierMapper.toEntity;
import static com.example.mscourier.mapper.CourierMapper.toResponse;

@Service
@RequiredArgsConstructor
public class CourierService {
    private final CourierRepository courierRepository;

    public List<CourierResponse> getAllCouriers() {
        return courierRepository.findAll()
                .stream()
                .map(c-> toResponse(c))
                .toList();
    }

    public CourierResponse getCourierById(Long id) {
        return toResponse(courierRepository.findById(id).orElse(null));
    }

    public List<CourierResponse> findAvailableCouriers() {
        return courierRepository.findByStatus(FREE)
                .stream()
                .map(c-> toResponse(c))
                .toList();
    }

    public void createCourier(CourierCreateRequest courierCreateRequest) {
        Courier courier = toEntity(courierCreateRequest);
        courierRepository.save(courier);
    }
}
