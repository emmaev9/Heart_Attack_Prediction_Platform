package com.user_microservice.config;

import com.user_microservice.dto.UserDetailsDTO;
import com.user_microservice.service.implementation.AdminService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig{

    @Bean
    CommandLineRunner commandLineRunner(AdminService adminService){
        return ags -> {
            UserDetailsDTO admin1 = new UserDetailsDTO(
                    "Emma-Evelin",
                    "Szakacs",
                    "admin",
                    "emma.e9@yahoo.com",
                    "admin"
            );
            if(!adminService.existsAdmin(admin1)){
                System.out.println(admin1.toString());
                adminService.insertAdmin(admin1);
            }
          //  userService.updateRole(admin1);
        };
    }
}
