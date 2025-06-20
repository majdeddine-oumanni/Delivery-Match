package com.delivery.backend.Service;

import com.delivery.backend.DTO.PackageRequestDTO;
import com.delivery.backend.Mappers.PackageRequestMapper;
import com.delivery.backend.Model.Package;
import com.delivery.backend.Model.PackageRequest;
import com.delivery.backend.Model.Sender;
import com.delivery.backend.Model.Trip;
import com.delivery.backend.Repositories.PackageRepository;
import com.delivery.backend.Repositories.PackageRequestRepository;

import com.delivery.backend.Repositories.TripRepository;
import com.delivery.backend.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageRequestService {
    private final PackageRequestRepository repository;
    private final PackageRequestMapper mapper;
    private final TripRepository tripRepository;
    private final UserRepository senderRepository;
    private final PackageRepository packageRepository;

    public PackageRequestService(PackageRequestRepository repository,
                                 PackageRequestMapper mapper,
                                 TripRepository tripRepository,
                                 UserRepository senderRepository,
                                 PackageRepository packageRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.tripRepository = tripRepository;
        this.senderRepository = senderRepository;
        this.packageRepository = packageRepository;
    }

    public PackageRequestDTO createRequest(PackageRequestDTO dto) {
        PackageRequest request = new PackageRequest();
        request.setTrip(tripRepository.findById(dto.getTripId())
                .orElseThrow(() -> new RuntimeException("Trip not found")));
        request.setSender((Sender) senderRepository.findById(dto.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found")));
        request.setaPackage(packageRepository.findById(dto.getPackageId())
                .orElseThrow(() -> new RuntimeException("Package not found")));
        request.setStatus(dto.getStatus() == null ? null : Enum.valueOf(
                request.getStatus().getClass(), dto.getStatus()));
        PackageRequest saved = repository.save(request);
        return mapper.toDTO(saved);
    }

    public List<PackageRequestDTO> getAllRequests() {
        return mapper.toDTOs(repository.findAll());
    }

    public void deleteRequest(Long id) {
        repository.deleteById(id);
    }
}
