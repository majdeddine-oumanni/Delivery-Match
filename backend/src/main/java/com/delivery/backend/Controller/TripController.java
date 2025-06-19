package com.delivery.backend.Controller;

import com.delivery.backend.DTO.TripDTO;
import com.delivery.backend.Service.TripService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {
    private final TripService service;

    public TripController(TripService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping("/post")
    public TripDTO postTrip(@RequestBody TripDTO dto){
        return service.postTrip(dto);
    }

    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/driversList/{id}")
    public List<TripDTO> getTrips(@PathVariable Long id){
        return service.getDriverTrips(id);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'SENDER')")
    @GetMapping("/list")
    public List<TripDTO> getTripsList(){
        return service.getAllTrips();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteTrip(@PathVariable Long id){
        service.deleteTrip(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public TripDTO updateTrip(@RequestBody TripDTO dto, @PathVariable Long id){
        return service.updateTrip(dto, id);
    }
}
