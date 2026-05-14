package com.example.mscourier.controller;

import com.example.mscourier.dao.entity.Courier;
import com.example.mscourier.dto.CourierCreateRequest;
import com.example.mscourier.dto.CourierResponse;
import com.example.mscourier.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/couriers")
public class CourierController {
    private final CourierService courierService;


    @GetMapping
    @ResponseStatus(OK)
    public List<CourierResponse> getAllCouriers() {
        return courierService.getAllCouriers();
    }
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CourierResponse getCourierById(@PathVariable Long id){
        return courierService.getCourierById(id);
    }

    @GetMapping("/available")
    @ResponseStatus(OK)
    public List<CourierResponse> getAvailableCouriers() {
        return courierService.findAvailableCouriers();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createCourier(@RequestBody CourierCreateRequest courierCreateRequest) {
        courierService.createCourier(courierCreateRequest);
    }
}
