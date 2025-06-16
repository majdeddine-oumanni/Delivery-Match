package com.delivery.backend.Service;

import com.delivery.backend.DTO.TripDTO;
import com.delivery.backend.Mappers.TripMapper;
import com.delivery.backend.Model.Trip;
import com.delivery.backend.Repositories.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    private final TripRepository repository;
    private final TripMapper mapper;

    public TripService(TripRepository repository, TripMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TripDTO postTrip(TripDTO dto){
        Trip trip = mapper.toEntity(dto);
        Trip savedTrip = repository.save(trip);
        return mapper.toDTO(savedTrip);
    }

    public List<TripDTO> getAllTrips(){
        List<Trip> trips = repository.findAll();
        return mapper.toDTOs(trips);
    }
}
