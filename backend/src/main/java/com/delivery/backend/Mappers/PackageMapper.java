package com.delivery.backend.Mappers;

import com.delivery.backend.DTO.PackageDTO;
import com.delivery.backend.Model.Package;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PackageMapper {
    Package toEntity(PackageDTO dto);
    PackageDTO toDTO(Package aPackage);
    List<PackageDTO> toDTOs(List<Package> packageList);
}
