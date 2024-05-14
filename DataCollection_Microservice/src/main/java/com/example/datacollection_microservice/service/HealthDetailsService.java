package com.example.datacollection_microservice.service;

import com.example.datacollection_microservice.dto.HealthDetailsDTO;
import com.example.datacollection_microservice.entity.HealthDetails;
import com.example.datacollection_microservice.entity.Patient;
import com.example.datacollection_microservice.exception.ResourceNotFoundException;
import com.example.datacollection_microservice.mapper.HealthDetailsMapper;
import com.example.datacollection_microservice.repository.HealthDetailsRepository;
import com.example.datacollection_microservice.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HealthDetailsService {
    private final HealthDetailsRepository healthDetailsRepository;
    private final PatientRepository patientRepository;
    private final HealthDetailsMapper healthDetailsMapper;
    private final static Logger LOGGER = LoggerFactory.getLogger(HealthDetailsService.class);

    @Transactional
    public UUID saveDetails(HealthDetailsDTO healthDetailsDTO){
        UUID patientId = healthDetailsDTO.getPatientId();
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient with id " + patientId + " was not found"));

        HealthDetails existingDetails = healthDetailsRepository.findByPatient_Id(patientId)
                .orElse(new HealthDetails());
        existingDetails.setPatient(patient);
        existingDetails.setStressLevel(healthDetailsDTO.getStressLevel());
        existingDetails.setDiabetes(healthDetailsDTO.getDiabetes());
        existingDetails.setOtherCondition(healthDetailsDTO.getOtherCondition());
        existingDetails.setSmokingStatus(healthDetailsDTO.getSmokingStatus());
        existingDetails.setHistoryOfHD(healthDetailsDTO.getHistoryOfHD());
        existingDetails.setBodyMassIndex(healthDetailsDTO.getBodyMassIndex());
        existingDetails.setPhysicalActivityLevel(healthDetailsDTO.getPhysicalActivityLevel());
        existingDetails.setHistoryOfHA(healthDetailsDTO.getHistoryOfHA());
        existingDetails.setAlcoholConsumption(healthDetailsDTO.getAlcoholConsumption());

        HealthDetails savedDetails = healthDetailsRepository.save(existingDetails);
        return savedDetails.getId();
    }

    public HealthDetailsDTO getHealthDetails(UUID patientId){
        System.out.println("Inainte de a cauta");
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if(!patientOptional.isPresent()){
            LOGGER.error("Patient with id {} was not found", patientId);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + "with id " + patientId);
        }
        System.out.println("A gasit pacientul");

        List<HealthDetails> allHealthDetails = healthDetailsRepository.findAll();
        if(allHealthDetails.isEmpty()) {
            return null;
        }
        for(HealthDetails healthDetails: allHealthDetails){
            if(healthDetails.getPatient().getId().equals(patientId)){
                HealthDetailsDTO healthDetailsDTO = healthDetailsMapper.modelToDto(healthDetails);
                return healthDetailsDTO;
            }
        }
        return null;
    }
}
