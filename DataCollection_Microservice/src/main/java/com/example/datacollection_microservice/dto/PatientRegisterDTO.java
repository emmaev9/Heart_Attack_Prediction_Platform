package com.example.datacollection_microservice.dto;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientRegisterDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String cnp;
    private String gender;
    private LocalDate birthday;
}
