package com.example.datacollection_microservice.dto;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetFamilyHistoryDTO {
    private UUID id;
    private UUID patientId;
    private String relationship;
    private String name;
    private String gender;
    private String currentAge;
    private String ageOfDeath;
    private String causeOfDeath;
    private String heartDisease;
    private String typeOfHeartDisease;
}

