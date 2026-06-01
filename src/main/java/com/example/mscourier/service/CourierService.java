package com.example.mscourier.service;

import com.example.mscourier.annotation.Loggable;
import com.example.mscourier.criteria.CourierCriteria;
import com.example.mscourier.criteria.PageCriteria;
import com.example.mscourier.dao.entity.Courier;
import com.example.mscourier.dao.repository.CourierRepository;
import com.example.mscourier.dto.CourierCreateRequestDto;
import com.example.mscourier.dto.CourierResponseDto;
import com.example.mscourier.enums.CourierStatus;
import com.example.mscourier.exceptions.CourierNotFoundException;
import com.example.mscourier.specification.CourierSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.mscourier.enums.CourierStatus.FREE;
import static com.example.mscourier.mapper.CourierMapper.*;

@Service
@RequiredArgsConstructor
@Loggable
public class CourierService {

    private final CourierRepository courierRepository;

    public List<CourierResponseDto> getAllCouriers() {
        return courierRepository.findAllWithProfile().stream()
                .map(c->toResponse(c))
                .toList();
    }

    public Page<CourierResponseDto> getOrders(CourierCriteria courierCriteria, PageCriteria pageCriteria) {
        PageRequest pageable = PageRequest.of(pageCriteria.getPageNumber(), pageCriteria.getCount());

        var courierPage = courierRepository.findAll(
                new CourierSpecification(courierCriteria),
                pageable
        );

        return courierPage.map(o ->toResponse(o));
    }

    public CourierResponseDto getCourierById(Long id) {
        return toResponse(fetchCourierIfExists(id));
    }

    public List<CourierResponseDto> findAvailableCouriers() {
        return courierRepository.findAllByStatusWithProfile(FREE).stream()
                .map(c->toResponse(c))
                .toList();
    }

    @Transactional
    public CourierResponseDto createCourier(CourierCreateRequestDto request) {
        var courier = createCourierEntity();
        var profile = toProfileEntity(request);
        profile.setCourier(courier);
        courier.setProfile(profile);
        courierRepository.save(courier);

        return toResponse(courier);
    }

    @Transactional
    public void markCourierBusy(Long courierId) {
        var courier = fetchCourierIfExists(courierId);
        if (courier.getStatus() == CourierStatus.BUSY) {
            throw new IllegalStateException("Courier is already busy");
        }
        courier.setStatus(CourierStatus.BUSY);
        courierRepository.save(courier);
    }

    @Transactional
    public void markCourierFree(Long courierId) {
        var courier = fetchCourierIfExists(courierId);
        if (courier.getStatus() == FREE) {
            throw new IllegalStateException("Courier is already free");
        }
        courier.setStatus(FREE);
        courierRepository.save(courier);
    }

    private Courier fetchCourierIfExists(Long id) {
        var courier = courierRepository.findByIdWithProfile(id);
        if (courier.isEmpty()) {
            throw new CourierNotFoundException(id);
        }
        return courier.get();
    }
}