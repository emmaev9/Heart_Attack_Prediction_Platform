package com.user_microservice.mapper;

import com.user_microservice.dto.UserDTO;
import com.user_microservice.dto.UserDetailsDTO;
import com.user_microservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO modelToDTO(User user){
        UserDTO userDetailsDTO = new UserDTO();
        userDetailsDTO.setId(user.getId());
        userDetailsDTO.setUsername(user.getUsername());
        userDetailsDTO.setEmail(user.getEmail());
        userDetailsDTO.setLastName(user.getLastName());
        userDetailsDTO.setFirstName(user.getFirstName());
        userDetailsDTO.setPassword(user.getPassword());
        userDetailsDTO.setPhone(user.getPhone());
        userDetailsDTO.setRole(user.getRole());
        return  userDetailsDTO;
    }

    public User DTOtoModel(UserDTO userDetailsDTO){
        User user = new User();
        user.setUsername(userDetailsDTO.getUsername());
        user.setEmail(userDetailsDTO.getEmail());
        user.setLastName(userDetailsDTO.getLastName());
        user.setFirstName(userDetailsDTO.getFirstName());
        user.setPassword(userDetailsDTO.getPassword());
        user.setId(userDetailsDTO.getId());
        user.setPhone(userDetailsDTO.getPhone());
        user.setRole(userDetailsDTO.getRole());
        return user;
    }
}
