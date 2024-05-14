package com.user_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class DoctorDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
}