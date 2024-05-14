package com.example.datacollection_microservice.mapper;

import com.example.datacollection_microservice.dto.HealthDetailsDTO;
import com.example.datacollection_microservice.entity.HealthDetails;
import com.example.datacollection_microservice.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class HealthDetailsMapper {
    public HealthDetails dtoToModel(HealthDetailsDTO healthDetailsDTO){
        HealthDetails healthDetails = new HealthDetails();
        healthDetails.setAlcoholConsumption(healthDetailsDTO.getAlcoholConsumption());
        healthDetails.setDiabetes(healthDetailsDTO.getDiabetes());
        healthDetails.setBodyMassIndex(healthDetailsDTO.getBodyMassIndex());
        healthDetails.setHistoryOfHA(healthDetailsDTO.getHistoryOfHA());
        healthDetails.setHistoryOfHD(healthDetailsDTO.getHistoryOfHD());
        healthDetails.setSmokingStatus(healthDetailsDTO.getSmokingStatus());
        healthDetails.setPhysicalActivityLevel(healthDetailsDTO.getPhysicalActivityLevel());
        healthDetails.setOtherCondition(healthDetailsDTO.getOtherCondition());
        healthDetails.setStressLevel(healthDetailsDTO.getStressLevel());
        return healthDetails;
    }
    public HealthDetailsDTO modelToDto(HealthDetails healthDetails){
        HealthDetailsDTO healthDetailsDTO = new HealthDetailsDTO();
        healthDetailsDTO.setPatientId(healthDetails.getPatient().getId());
        healthDetailsDTO.setAlcoholConsumption(healthDetails.getAlcoholConsumption());
        healthDetailsDTO.setDiabetes(healthDetails.getDiabetes());
        healthDetailsDTO.setBodyMassIndex(healthDetails.getBodyMassIndex());
        healthDetailsDTO.setHistoryOfHA(healthDetails.getHistoryOfHA());
        healthDetailsDTO.setHistoryOfHD(healthDetails.getHistoryOfHD());
        healthDetailsDTO.setSmokingStatus(healthDetails.getSmokingStatus());
        healthDetailsDTO.setPhysicalActivityLevel(healthDetails.getPhysicalActivityLevel());
        healthDetailsDTO.setOtherCondition(healthDetails.getOtherCondition());
        healthDetailsDTO.setStressLevel(healthDetails.getStressLevel());
        return healthDetailsDTO;
    }
}
