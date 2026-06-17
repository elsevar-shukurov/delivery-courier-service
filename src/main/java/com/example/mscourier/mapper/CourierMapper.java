package com.example.mscourier.mapper;

import com.example.mscourier.dao.entity.Courier;
import com.example.mscourier.dao.entity.CourierProfile;
import com.example.mscourier.dto.CourierCreateRequestDto;
import com.example.mscourier.dto.CourierResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourierMapper {

    CourierProfile toProfileEntity(CourierCreateRequestDto request);

    @Mapping(source = "profile.name", target = "name")
    @Mapping(source = "profile.surname", target = "surname")
    @Mapping(source = "profile.phoneNumber", target = "phoneNumber")
    @Mapping(source = "profile.vehicleType", target = "vehicleType")
    @Mapping(source = "profile.licensePlate", target = "licensePlate")
    CourierResponseDto toResponse(Courier courier);

    default Courier createCourierWithProfile(CourierCreateRequestDto request) {
        var courier = Courier.builder().build();
        var profile = toProfileEntity(request);
        profile.setCourier(courier);
        courier.setProfile(profile);
        return courier;
    }
}