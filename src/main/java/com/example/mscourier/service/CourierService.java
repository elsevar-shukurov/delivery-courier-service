package com.example.mscourier.service;

import com.example.mscourier.annotation.Loggable;
import com.example.mscourier.criteria.CourierCriteria;
import com.example.mscourier.criteria.PageCriteria;
import com.example.mscourier.dao.entity.Courier;
import com.example.mscourier.dao.repository.CourierRepository;
import com.example.mscourier.dto.CourierCreateRequestDto;
import com.example.mscourier.dto.CourierResponseDto;
import com.example.mscourier.exceptions.CourierNotFoundException;
import com.example.mscourier.mapper.CourierMapper;
import com.example.mscourier.specification.CourierSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mscourier.enums.CourierStatus.BUSY;
import static com.example.mscourier.enums.CourierStatus.FREE;

@Service
@RequiredArgsConstructor
@Loggable
public class CourierService {

    private final CourierRepository courierRepository;
    private final CourierMapper courierMapper;

    public Page<CourierResponseDto> getCouriers(CourierCriteria orderCriteria, PageCriteria pageCriteria) {

        PageRequest pageable = PageRequest.of(
                pageCriteria.getPageNumber(),
                pageCriteria.getCount(),
                Sort.by(Sort.Direction.fromString(pageCriteria.getSortDirection().toUpperCase()), pageCriteria.getSortBy())
        );

        return courierRepository.findAll(new CourierSpecification(orderCriteria), pageable)
                .map(o-> courierMapper.toResponse(o));
    }

    public CourierResponseDto getCourierById(Long id) {
        return courierMapper.toResponse(fetchCourierWithProfile(id));
    }

    public List<CourierResponseDto> findAvailableCouriers() {
        return courierRepository.findAllByStatusWithProfile(FREE).stream()
                .map(c->courierMapper.toResponse(c))
                .toList();
    }

    public CourierResponseDto createCourier(CourierCreateRequestDto request) {
        var courier = courierMapper.createCourierWithProfile(request);
        courierRepository.save(courier);
        return courierMapper.toResponse(courier);
    }

    public void markCourierBusy(Long courierId) {
        var courier = fetchCourierById(courierId);
        if (BUSY.equals(courier.getStatus())) {
            throw new IllegalStateException("Courier is already busy");
        }
        courier.setStatus(BUSY);
        courierRepository.save(courier);
    }

    public void markCourierFree(Long courierId) {
        var courier = fetchCourierById(courierId);
        if (FREE.equals(courier.getStatus())) {
            throw new IllegalStateException("Courier is already free");
        }
        courier.setStatus(FREE);
        courierRepository.save(courier);
    }

    private Courier fetchCourierById(Long id) {
        return courierRepository.findByIdWithProfile(id)
                .orElseThrow(() -> new CourierNotFoundException(id));
    }
    private Courier fetchCourierWithProfile(Long id) {
        return courierRepository.findByIdWithProfile(id)
                .orElseThrow(() -> new CourierNotFoundException(id));
    }
}