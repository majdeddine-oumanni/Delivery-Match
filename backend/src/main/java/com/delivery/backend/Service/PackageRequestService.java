package com.delivery.backend.Service;

import com.delivery.backend.DTO.PackageRequestDTO;
import com.delivery.backend.Mappers.PackageRequestMapper;
import com.delivery.backend.Model.*;
import com.delivery.backend.Repositories.PackageRequestRepository;

import com.delivery.backend.Repositories.TripRepository;
import com.delivery.backend.Repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageRequestService {
    private final PackageRequestRepository repository;
    private final PackageRequestMapper mapper;
    private final UserRepository senderRepository;
    private final TripRepository tripRepository;

    public PackageRequestService(PackageRequestRepository repository,
                                 PackageRequestMapper mapper,
                                 UserRepository senderRepository, TripRepository tripRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.senderRepository = senderRepository;
        this.tripRepository = tripRepository;
    }

    public PackageRequestDTO createRequest(PackageRequestDTO dto) {
        PackageRequest request = mapper.toEntity(dto);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Trip trip = tripRepository.findById(request.getTripId())
                .orElseThrow(()-> new RuntimeException("trip not found"));
        request.setTrip(trip);
        Sender sender = (Sender) senderRepository.findByEmail(email);
        request.setSender(sender);
        PackageRequest saved = repository.save(request);
        return mapper.toDTO(saved);
    }

    public PackageRequestDTO updateRequest(RequestStatus requestStatus, Long id){
        PackageRequest request = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("request not found"));
        request.setStatus(requestStatus);
        PackageRequest savedRequest = repository.save(request);
        return mapper.toDTO(savedRequest);
    }

    public List<PackageRequestDTO> getAllRequests() {
        return mapper.toDTOs(repository.findAll());
    }

    public List<PackageRequestDTO> getAllSenderRequests(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Sender sender = (Sender) senderRepository.findByEmail(email);
        List<PackageRequest> requests = repository.findAllBySenderId(sender.getId());
        return mapper.toDTOs(requests);
    }

    public void deleteRequest(Long id) {
        repository.deleteById(id);
    }
}
