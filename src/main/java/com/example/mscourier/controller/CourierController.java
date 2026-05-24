package com.example.mscourier.controller;

import com.example.mscourier.dto.CourierCreateRequest;
import com.example.mscourier.dto.CourierResponse;
import com.example.mscourier.enums.CourierStatus;
import com.example.mscourier.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/couriers")
public class CourierController {
    private final CourierService courierService;


    @GetMapping
    public List<CourierResponse> getAllCouriers() {
        return courierService.getAllCouriers();
    }
    @GetMapping("/{id}")
    public CourierResponse getCourierById(@PathVariable Long id){
        return courierService.getCourierById(id);
    }

    @GetMapping("/available")
    public List<CourierResponse> getAvailableCouriers() {
        return courierService.findAvailableCouriers();
    }
    @PutMapping("/{id}/status")
    public void updateCourierStatus(@PathVariable Long id, @RequestParam CourierStatus status){
        courierService.updateCourierStatus(id, status);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createCourier(@RequestBody CourierCreateRequest courierCreateRequest) {
        courierService.createCourier(courierCreateRequest);
    }
}
