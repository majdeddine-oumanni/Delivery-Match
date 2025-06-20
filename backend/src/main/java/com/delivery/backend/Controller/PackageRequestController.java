package com.delivery.backend.Controller;

import com.delivery.backend.DTO.PackageRequestDTO;
import com.delivery.backend.Service.PackageRequestService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<PackageRequestDTO> getAll() {
        return service.getAllRequests();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteRequest(id);
    }
}
