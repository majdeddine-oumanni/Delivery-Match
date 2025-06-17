package com.delivery.backend.Service;

import com.delivery.backend.DTO.UserDTO;
import com.delivery.backend.Mappers.UserMapper;
import com.delivery.backend.Model.User;
import com.delivery.backend.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserMapper mapper;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper mapper, UserRepository repository, PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }
    public UserDTO updateUser(UserDTO dto, Long id){
        User user = repository.findById(id).orElse(null);
        if (user == null){
            throw new RuntimeException("user not found");
        }
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return mapper.toDTO(user);
    }

    public List<UserDTO> getUsersList(){
        List<User> users = repository.findAll();
        return mapper.toDTOs(users);
    }

    public void deleteUser(Long id){
        repository.deleteById(id);
    }
}
