package com.example.datacollection_microservice.service;

import com.example.datacollection_microservice.dto.DoctorDTO;
import com.example.datacollection_microservice.dto.PatientDTO;
import com.example.datacollection_microservice.entity.Doctor;
import com.example.datacollection_microservice.entity.Patient;
import com.example.datacollection_microservice.exception.ResourceNotFoundException;
import com.example.datacollection_microservice.mapper.DoctorMapper;
import com.example.datacollection_microservice.mapper.PatientMapper;
import com.example.datacollection_microservice.repository.DoctorRepository;
import com.example.datacollection_microservice.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;
    private final static Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);

    public DoctorDTO saveDoctor(DoctorDTO doctorDTO){
        Doctor doctor = doctorMapper.dtoToModel(doctorDTO);
        doctor.setPatients(new ArrayList<>());
        doctor = doctorRepository.save(doctor);
        return doctorMapper.modelToDto(doctor);
    }
    public List<DoctorDTO> getDoctors(){
        List<Doctor> doctorList = doctorRepository.findAll();
        List<DoctorDTO> doctorDTOList = new ArrayList<>();
        for(Doctor doctor: doctorList){
            DoctorDTO doctorDTO = doctorMapper.modelToDto(doctor);
            doctorDTOList.add(doctorDTO);
        }
        return doctorDTOList;
    }

    public void deleteDoctor(UUID id){
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if(!doctorOptional.isPresent()){
            LOGGER.error("Doctor with id {} was not found", id);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + "with id " + id);
        }
        doctorRepository.delete(doctorOptional.get());
    }

    public DoctorDTO updateDoctor(DoctorDTO doctorDTO){
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorDTO.getId());
        if(!doctorOptional.isPresent()){
            LOGGER.error("Doctor with id {} was not found", doctorDTO.getId());
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + "with id " + doctorDTO.getId());
        }
        Doctor doctor = doctorOptional.get();
        doctor.setUsername(doctorDTO.getUsername());
        doctor.setLastName(doctorDTO.getLastName());
        doctor.setFirstName(doctorDTO.getFirstName());
        doctor = doctorRepository.save(doctor);
        return doctorMapper.modelToDto(doctor);
    }
    @Transactional
    public void setPatientToDoctor(UUID patientId, UUID doctorId){
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if(!patientOptional.isPresent()){
            LOGGER.error("Patient with id {} was not found", patientId);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + "with id " + patientId);
        }
        System.out.println("S-a gasit pacientul");
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if(!doctorOptional.isPresent()){
            LOGGER.error("Doctor with id {} was not found", doctorId);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + "with id " + doctorId);
        }
        System.out.println("S-a gasit doctorul");
        Patient patient = patientOptional.get();
        patient.setDoctor(doctorOptional.get());
        patientRepository.save(patient);
    }
    @Transactional
    public DoctorDTO getDoctorForPatient(UUID patientId){
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if(!patientOptional.isPresent()){
            LOGGER.error("Patient with id {} was not found", patientId);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + "with id " + patientId);
        }
        Doctor doctor = patientOptional.get().getDoctor();
        return doctorMapper.modelToDto(doctor);
    }


    public List<PatientDTO> getAllPatientsOfDoctor(UUID id){
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if(!doctorOptional.isPresent()){
            LOGGER.error("Doctor with id {} was not found", id);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + "with id " + id);
        }
        List<Patient> allPatients = patientRepository.findAll();
        List<PatientDTO> allPatientsDTO = new ArrayList<>();
        for(Patient patient: allPatients){
            if(patient.getDoctor()!= null && patient.getDoctor().getId().equals(id)){
                allPatientsDTO.add(patientMapper.modelToDTO(patient));
            }
        }
        return allPatientsDTO;
    }
}
