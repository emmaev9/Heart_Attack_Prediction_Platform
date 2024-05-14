package com.example.datacollection_microservice.mapper;

import com.example.datacollection_microservice.dto.DoctorDTO;
import com.example.datacollection_microservice.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    public Doctor dtoToModel(DoctorDTO doctorDTO){
        Doctor doctor = new Doctor();
        doctor.setId(doctorDTO.getId());
        doctor.setFirstName(doctorDTO.getFirstName());
        doctor.setLastName(doctorDTO.getLastName());
        doctor.setUsername(doctorDTO.getUsername());
        return doctor;
    }

    public DoctorDTO modelToDto(Doctor doctor){
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setUsername(doctor.getUsername());
        doctorDTO.setLastName(doctor.getLastName());
        doctorDTO.setFirstName(doctor.getFirstName());
        return doctorDTO;
    }
}
