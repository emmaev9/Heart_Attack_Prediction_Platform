package com.example.datacollection_microservice.dto;

import com.example.datacollection_microservice.entity.Patient;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FamilyHistoryDTO {
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
