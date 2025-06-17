package com.delivery.backend.Controller;

import com.delivery.backend.DTO.TripDTO;
import com.delivery.backend.Service.TripService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {
    private final TripService service;

    public TripController(TripService service) {
        this.service = service;
    }

    @PostMapping("/post")
    public TripDTO postTrip(@RequestBody TripDTO dto){
        return service.postTrip(dto);
    }

    @GetMapping("/list/{id}")
    public List<TripDTO> getTrips(@PathVariable Long id){
        return service.getAllTrips(id);
    }
}
