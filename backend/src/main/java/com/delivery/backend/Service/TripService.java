package com.delivery.backend.Service;

import com.delivery.backend.DTO.TripDTO;
import com.delivery.backend.Mappers.TripMapper;
import com.delivery.backend.Model.Driver;
import com.delivery.backend.Model.Trip;
import com.delivery.backend.Repositories.TripRepository;
import com.delivery.backend.Repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.security.PublicKey;
import java.util.List;

@Service
public class TripService {
    private final TripRepository repository;
    private final TripMapper mapper;
    private final UserRepository userRepository;


    public TripService(TripRepository repository, TripMapper mapper, UserRepository userRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    public TripDTO postTrip(TripDTO dto) {
        Trip trip = mapper.toEntity(dto);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Driver driver = (Driver) userRepository.findByEmail(email);
        trip.setDriver(driver);
        Trip savedTrip = repository.save(trip);
        return mapper.toDTO(savedTrip);
    }

    public List<TripDTO> getDriverTrips(Long id){
        List<Trip> trips = repository.findAllByDriver_Id(id);
        return mapper.toDTOs(trips);
    }

    public List<TripDTO> getAllTrips(){
        List<Trip> trips = repository.findAll();
        return mapper.toDTOs(trips);
    }

    public void deleteTrip(Long id){
        repository.deleteById(id);
    }

    public TripDTO updateTrip(TripDTO dto, Long id){
        Trip foundTrip = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("trip not found"));
        foundTrip.setDestination(dto.getDestination());
        foundTrip.setMaxPackageCapacity(dto.getMaxPackageCapacity());
        foundTrip.setStart(dto.getStart());
        foundTrip.setWayPoints(dto.getWayPoints());
        return mapper.toDTO(foundTrip);
    }
}
