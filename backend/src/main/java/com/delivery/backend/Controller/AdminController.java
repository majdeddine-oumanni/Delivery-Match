package com.delivery.backend.Controller;

import com.delivery.backend.DTO.UserDTO;
import com.delivery.backend.Model.User;
import com.delivery.backend.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService service;

    public AdminController(UserService service) {
        this.service = service;
    }

    @GetMapping("/userList")
    public List<UserDTO> getUsers(){
        return service.getUsersList();
    }

}
