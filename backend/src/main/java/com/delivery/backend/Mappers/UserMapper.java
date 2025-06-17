package com.delivery.backend.Mappers;

import com.delivery.backend.DTO.UserDTO;
import com.delivery.backend.Model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO dto);
    UserDTO toDTO(User user);
    List<UserDTO> toDTOs(List<User> users);
}
