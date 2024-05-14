package com.user_microservice.service;

import com.user_microservice.dto.InsertUserDTO;
import com.user_microservice.dto.UserDTO;
import com.user_microservice.entity.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    UUID insertUser(InsertUserDTO userDTO);
    UUID deleteUser(UUID userId);
    List<User> getDoctors();
    List<User> getMedicalAssistants();
    User editUser(UserDTO userDTO);
    public boolean isUsernameTaken(String user);
}
