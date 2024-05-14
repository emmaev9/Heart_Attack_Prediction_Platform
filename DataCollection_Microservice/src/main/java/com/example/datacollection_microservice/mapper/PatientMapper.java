package com.example.datacollection_microservice.mapper;

import com.example.datacollection_microservice.dto.PatientDTO;
import com.example.datacollection_microservice.dto.PatientEditDTO;
import com.example.datacollection_microservice.dto.PatientRegisterDTO;
import com.example.datacollection_microservice.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {


    public Patient registerDTOToModel(PatientRegisterDTO patientRegisterDTO){
        Patient patient = new Patient();
        patient.setFirstName(patientRegisterDTO.getFirstName());
        patient.setLastName(patientRegisterDTO.getLastName());
        patient.setBirthday(patientRegisterDTO.getBirthday());
        patient.setCNP(patientRegisterDTO.getCnp());
        patient.setPhone(patientRegisterDTO.getPhone());
        patient.setEmail(patientRegisterDTO.getEmail());
        patient.setGender(patientRegisterDTO.getGender());
        return patient;
    }

    public PatientDTO modelToDTO(Patient patient){
        PatientDTO patientDto = new PatientDTO();
        patientDto.setCnp(patient.getCNP());
        patientDto.setPhone(patient.getPhone());
        patientDto.setFirstName(patient.getFirstName());
        patientDto.setLastName(patient.getLastName());
        patientDto.setEmail(patient.getEmail());
        patientDto.setBirthday(patient.getBirthday());
        patientDto.setGender(patient.getGender());
        patientDto.setId(patient.getId());
        return patientDto;
    }

    public Patient editDTOToModel(PatientEditDTO patientEditDTO){
        Patient patient = new Patient();
        patient.setId(patientEditDTO.getId());
        patient.setFirstName(patientEditDTO.getFirstName());
        patient.setLastName(patientEditDTO.getLastName());
        patient.setCNP(patientEditDTO.getCnp());
        patient.setPhone(patientEditDTO.getPhone());
        patient.setEmail(patientEditDTO.getEmail());
        patient.setGender(patientEditDTO.getGender());
        patient.setBirthday(patientEditDTO.getBirthday());
        return patient;
    }
}
