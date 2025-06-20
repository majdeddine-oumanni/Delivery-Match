package com.delivery.backend.Controller;

import com.delivery.backend.DTO.PackageDTO;
import com.delivery.backend.Service.PackageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/package")
@CrossOrigin("*")
public class PackageController {
    private final PackageService service;

    public PackageController(PackageService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('SENDER')")
    @PostMapping("/add")
    public PackageDTO add(@RequestBody PackageDTO dto){
        return service.addPackage(dto);
    }

    @PreAuthorize("hasRole('SENDER')")
    @GetMapping("/getBySender/{id}")
    public List<PackageDTO> getBySender(@PathVariable Long id){
        return service.getAllBySenderId(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public List<PackageDTO> getAll(){
        return service.getAllPackages();
    }

    @PreAuthorize("hasRole('SENDER')")
    @PutMapping("/update/{id}")
    public PackageDTO update(@PathVariable Long id, @RequestBody PackageDTO dto){
        return service.updatePackage(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        service.deletePackage(id);
    }
}
