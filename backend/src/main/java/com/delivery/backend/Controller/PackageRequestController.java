package com.delivery.backend.Controller;

import com.delivery.backend.DTO.PackageRequestDTO;
import com.delivery.backend.Model.RequestStatus;
import com.delivery.backend.Service.PackageRequestService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
@CrossOrigin("*")
public class PackageRequestController {
    private final PackageRequestService service;

    public PackageRequestController(PackageRequestService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('SENDER')")
    @PostMapping("/create")
    public PackageRequestDTO createRequest(@RequestBody PackageRequestDTO dto) {
        return service.createRequest(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    @GetMapping("/all")
    public List<PackageRequestDTO> getAll() {
        return service.getAllRequests();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteRequest(id);
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PutMapping("/update/{id}")
    public PackageRequestDTO updateStatus(@RequestParam RequestStatus requestStatus, @PathVariable Long id){
        return service.updateRequest(requestStatus, id);
    }

    @PreAuthorize("hasRole('SENDER')")
    @GetMapping("/sender")
    public List<PackageRequestDTO> getAllSenderRequests(){
        return service.getAllSenderRequests();
    }
}
