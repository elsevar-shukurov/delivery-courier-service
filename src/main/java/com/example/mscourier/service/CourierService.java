package com.example.mscourier.service;

import com.example.mscourier.dao.entity.Courier;
import com.example.mscourier.dao.repository.CourierRepository;
import com.example.mscourier.dto.CourierCreateRequest;
import com.example.mscourier.dto.CourierResponse;
import com.example.mscourier.enums.CourierStatus;
import com.example.mscourier.exceptions.CourierNotFoundException;
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
        return courierRepository.findById(id)
                .map(c -> toResponse(c))
                .orElseThrow(() -> new CourierNotFoundException(id));
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

    public void updateCourierStatus(Long id, CourierStatus status) {
        var courier = fetchCourierIfExists(id);

        courier.setStatus(status);
        courierRepository.save(courier);
    }

    private Courier fetchCourierIfExists(Long id) {
        return courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException(id));
    }
}
