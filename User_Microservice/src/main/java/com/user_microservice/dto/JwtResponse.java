package com.user_microservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@NoArgsConstructor
@Setter
@Getter

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private UUID id;
    private String password;
    private String username;
    private String role;

    public JwtResponse(String accessToken,UUID id,String password, String username, String role) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
