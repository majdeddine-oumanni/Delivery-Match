package com.delivery.backend.Controller;

import com.delivery.backend.DTO.UserDTO;
import com.delivery.backend.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PutMapping("/update/{id}")
    public UserDTO updateUser(@RequestBody UserDTO dto, @PathVariable Long id){
        return service.updateUser(dto, id);
    }

}
