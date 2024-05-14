package com.example.datacollection_microservice.service;

import com.example.datacollection_microservice.dto.RegisterVisitDTO;
import com.example.datacollection_microservice.dto.VisitDTO;
import com.example.datacollection_microservice.entity.Patient;
import com.example.datacollection_microservice.entity.Visit;
import com.example.datacollection_microservice.exception.ResourceNotFoundException;
import com.example.datacollection_microservice.mapper.VisitMapper;
import com.example.datacollection_microservice.repository.PatientRepository;
import com.example.datacollection_microservice.repository.VisitRepository;
import jakarta.persistence.SecondaryTable;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final VisitMapper visitMapper;
    private final static Logger LOGGER = LoggerFactory.getLogger(VisitService.class);

    public Visit saveVisit(RegisterVisitDTO registerVisitDTO){
        Optional<Patient> patientOptional = patientRepository.findById(registerVisitDTO.getPatientId());
        if(!patientOptional.isPresent()){
            LOGGER.error("Patient with id {} was not found", registerVisitDTO.getPatientId());
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + "with id " +registerVisitDTO.getPatientId());
        }
        Visit newVisit = visitMapper.registerDtoToModel(registerVisitDTO,patientOptional.get());
        newVisit = visitRepository.save(newVisit);
        return newVisit;
    }
    public Visit updateVisit(VisitDTO visitDTO){
        Optional<Visit> visitOptional = visitRepository.findById(visitDTO.getId());
        if(!visitOptional.isPresent()){
            LOGGER.error("Visit with id {} was not found", visitDTO.getId());
            throw new ResourceNotFoundException(Visit.class.getSimpleName() + "with id " + visitDTO.getId());
        }
        Optional<Patient> patientOptional = patientRepository.findById(visitDTO.getPatientId());
        if(!patientOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found", visitDTO.getPatientId());
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + "with id " + visitDTO.getPatientId());
        }
        Visit updatedVisit = visitMapper.dtoToModel(visitDTO);
        updatedVisit.setPatient(patientOptional.get());

        updatedVisit = visitRepository.save(updatedVisit);
        return updatedVisit;
    }

    public List<VisitDTO> getPatientVisits(UUID patientId){
        List<Visit> allVisits = visitRepository.findAll();
        List<VisitDTO> allPatientVisits = new ArrayList<>();
        if(allVisits.isEmpty()){
            return null;
        }

        for(Visit visit: allVisits){
            if(visit.getPatient().getId().equals(patientId)){
                VisitDTO visitDTO = visitMapper.modelToDTO(visit);
                allPatientVisits.add(visitDTO);
            }
        }
        return allPatientVisits;
    }

    public VisitDTO getVisitById(UUID visitId){
        Optional<Visit> visitOptional = visitRepository.findById(visitId);
        if(!visitOptional.isPresent()){
            LOGGER.error("Visit with id {} was not found", visitId);
            throw new ResourceNotFoundException(Visit.class.getSimpleName() + "with id " + visitId);
        }
        VisitDTO visitDTO = visitMapper.modelToDTO(visitOptional.get());
        return visitDTO;
    }

    public VisitDTO getLatestVisitOfPatient(UUID patientId){
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if(!patientOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found", patientId);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + "with id " + patientId);
        }
        List<Visit> allVisits = visitRepository.findAll();
        List<Visit> patientVisits = new ArrayList<>();

        for (Visit visit : allVisits) {
            if (visit.getPatient().getId().equals(patientId)) {
                patientVisits.add(visit);
            }
        }
        Collections.sort(patientVisits, Comparator.comparing(Visit::getVisitDate).reversed());
        if (patientVisits.isEmpty()) {
            return null;
        }
        Visit latestVisit = patientVisits.get(0);
        return visitMapper.modelToDTO(latestVisit);
    }
}
