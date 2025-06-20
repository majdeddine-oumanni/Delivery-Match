package com.delivery.backend.Controller;

import com.delivery.backend.DTO.UserDTO;
import com.delivery.backend.Service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PutMapping("/update/{id}")
    public UserDTO updateUser(@RequestBody UserDTO dto, @PathVariable Long id){
        return service.updateUser(dto, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/userList")
    public List<UserDTO> getUsers(){
        return service.getUsersList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }

}
