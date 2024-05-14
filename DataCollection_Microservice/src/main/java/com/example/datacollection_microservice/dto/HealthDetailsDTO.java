package com.example.datacollection_microservice.dto;

import com.example.datacollection_microservice.entity.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HealthDetailsDTO {
    private UUID patientId;
    private String smokingStatus;
    private String alcoholConsumption;
    private String physicalActivityLevel;
    private String stressLevel;
    private String bodyMassIndex;
    private String diabetes;
    private String historyOfHD; //HD aka heart disease
    private String historyOfHA; //HA aka heart attack
    private String otherCondition;
}
