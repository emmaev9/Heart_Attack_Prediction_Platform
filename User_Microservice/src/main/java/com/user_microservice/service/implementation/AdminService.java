package com.user_microservice.service.implementation;

import com.user_microservice.controller.handlers.exceptions.ResourceNotFoundException;
import com.user_microservice.dto.InsertUserDTO;
import com.user_microservice.dto.UserDTO;
import com.user_microservice.dto.UserDetailsDTO;
import com.user_microservice.entity.User;
import com.user_microservice.mapper.UserMapper;
import com.user_microservice.repository.UserRepository;
import com.user_microservice.service.IAdminService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class AdminService implements IAdminService {
    public final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);

    @Override
    public boolean existsAdmin(UserDetailsDTO admin) {
        return userRepository.existsByUsername(admin.getUsername());
    }

    @Override
    public UUID insertAdmin(UserDetailsDTO userDetailsDTO) {
        User user = new User();
        user.setUsername(userDetailsDTO.getUsername());
        user.setFirstName(userDetailsDTO.getFirstName());
        user.setLastName(userDetailsDTO.getLastName());
        user.setEmail(userDetailsDTO.getEmail());
        user.setRole("ADMIN");
        user.setPhone("0742705530");
        user.setPassword(encoder.encode(userDetailsDTO.getPassword()));
        user = userRepository.save(user);
        LOGGER.debug("Person with id {} was inserted in db", user.getId());
        return user.getId();
    }

}
