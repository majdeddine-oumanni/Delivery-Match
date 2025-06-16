package com.delivery.backend.Mappers;

import com.delivery.backend.DTO.TripDTO;
import com.delivery.backend.Model.Trip;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TripMapper {
    Trip toEntity(TripDTO dto);
    TripDTO toDTO(Trip trip);
    List<TripDTO> toDTOs(List<Trip> trips);
}
