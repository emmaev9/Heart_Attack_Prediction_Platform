package com.example.datacollection_microservice.service;

import com.example.datacollection_microservice.dto.PatientDTO;
import com.example.datacollection_microservice.dto.PatientEditDTO;
import com.example.datacollection_microservice.dto.PatientRegisterDTO;
import com.example.datacollection_microservice.entity.HealthDetails;
import com.example.datacollection_microservice.entity.Patient;
import com.example.datacollection_microservice.exception.ResourceNotFoundException;
import com.example.datacollection_microservice.mapper.PatientMapper;
import com.example.datacollection_microservice.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor

public class PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);

    @Transactional
    public PatientDTO savePatient(PatientRegisterDTO patientRegisterDTO){
        Patient newPatient = patientMapper.registerDTOToModel(patientRegisterDTO);
        newPatient.setFamilyHistories(new ArrayList<>());
        newPatient.setVisits(new ArrayList<>());
        //newPatient.setHealthDetails(new HealthDetails());
        newPatient = patientRepository.save(newPatient);
        PatientDTO patientDTO = patientMapper.modelToDTO(newPatient);

        return patientDTO;
    }

    public List<PatientDTO> findAll(){
        List<Patient> allPatients = patientRepository.findAll();
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for(Patient patient: allPatients){
           PatientDTO dto = patientMapper.modelToDTO(patient);
           patientDTOList.add(dto);
        }
        return patientDTOList;
    }

    public PatientDTO findPatient(UUID id){
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if(!patientOptional.isPresent()){
            LOGGER.error("Patient with id {} was not found", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + "with id " + id);
        }
        PatientDTO patientDTO = patientMapper.modelToDTO(patientOptional.get());
        return patientDTO;
    }

    public UUID deletePatient(UUID id){
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if(!patientOptional.isPresent()){
            LOGGER.error("Patient with id {} was not found", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + "with id " + id);
        }
        patientRepository.delete(patientOptional.get());
        return id;
    }

    @Transactional
    public Patient updatePatient(PatientEditDTO patientEditDTO){
        Optional<Patient> patientOptional = patientRepository.findById(patientEditDTO.getId());
        if(!patientOptional.isPresent()){
            LOGGER.error("Patient with id {} was not found", patientEditDTO.getId());
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + "with id " + patientEditDTO.getId());
        }
        Patient editedPatient = patientMapper.editDTOToModel(patientEditDTO);
        editedPatient.setDoctor(patientOptional.get().getDoctor());
        editedPatient.setHealthDetails(patientOptional.get().getHealthDetails());
        editedPatient.setFamilyHistories(patientOptional.get().getFamilyHistories());
        editedPatient = patientRepository.save(editedPatient);

        return editedPatient;
    }

}
