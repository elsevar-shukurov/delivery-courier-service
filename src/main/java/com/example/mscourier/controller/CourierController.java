package com.example.mscourier.controller;

import com.example.mscourier.criteria.CourierCriteria;
import com.example.mscourier.criteria.PageCriteria;
import com.example.mscourier.dto.CourierCreateRequestDto;
import com.example.mscourier.dto.CourierResponseDto;
import com.example.mscourier.service.CourierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/couriers")
public class CourierController {

    private final CourierService courierService;


    @GetMapping
    public Page<CourierResponseDto> getOrders(PageCriteria pageCriteria, CourierCriteria orderCriteria) {
        return courierService.getOrders(orderCriteria, pageCriteria);
    }

    @GetMapping("/{id}")
    public CourierResponseDto getCourierById(@PathVariable Long id) {
        return courierService.getCourierById(id);
    }

    @GetMapping("/available")
    public List<CourierResponseDto> getAvailableCouriers() {
        return courierService.findAvailableCouriers();
    }


    @PostMapping
    @ResponseStatus(CREATED)
    public CourierResponseDto createCourier(@Valid @RequestBody CourierCreateRequestDto request) {
        return courierService.createCourier(request);
    }
}