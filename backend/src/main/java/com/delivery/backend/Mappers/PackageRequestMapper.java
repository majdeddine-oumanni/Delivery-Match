package com.delivery.backend.Mappers;

import com.delivery.backend.DTO.PackageRequestDTO;
import com.delivery.backend.Model.PackageRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PackageRequestMapper {
    PackageRequest toEntity(PackageRequestDTO dto);
    PackageRequestDTO toDTO(PackageRequest entity);
    List<PackageRequestDTO> toDTOs(List<PackageRequest> entities);
}

