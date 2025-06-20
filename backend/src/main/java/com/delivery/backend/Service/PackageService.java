package com.delivery.backend.Service;

import com.delivery.backend.DTO.PackageDTO;
import com.delivery.backend.Mappers.PackageMapper;
import com.delivery.backend.Model.Package;
import com.delivery.backend.Repositories.PackageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {
    private final PackageMapper mapper;
    private final PackageRepository repository;

    public PackageService(PackageMapper mapper, PackageRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public PackageDTO addPackage(PackageDTO dto) {
        Package aPackage = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(aPackage));
    }

    public List<PackageDTO> getAllPackages() {
        return mapper.toDTOs(repository.findAll());
    }

    public List<PackageDTO> getAllBySenderId(Long senderId) {
        List<Package> packages = repository.findBySenderId(senderId);
        return mapper.toDTOs(packages);
    }

    public PackageDTO updatePackage(Long id, PackageDTO dto) {
        Package found = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));
        found.setHeight(dto.getHeight());
        found.setDescription(dto.getDescription());
        found.setLength(dto.getLength());
        found.setWidth(dto.getWidth());
        return mapper.toDTO(repository.save(found));
    }

    public void deletePackage(Long id) {
        repository.deleteById(id);
    }
}
