package com.user_microservice.service;

import com.user_microservice.dto.UserDetailsDTO;

import java.util.UUID;


public interface IAdminService {
    boolean existsAdmin(UserDetailsDTO admin);
    UUID insertAdmin(UserDetailsDTO userDetailsDTO);
}
