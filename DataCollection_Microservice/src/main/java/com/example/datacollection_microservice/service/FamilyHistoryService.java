package com.example.datacollection_microservice.service;

import com.example.datacollection_microservice.dto.FamilyHistoryDTO;
import com.example.datacollection_microservice.dto.GetFamilyHistoryDTO;
import com.example.datacollection_microservice.entity.FamilyHistory;
import com.example.datacollection_microservice.entity.Patient;
import com.example.datacollection_microservice.exception.ResourceNotFoundException;
import com.example.datacollection_microservice.mapper.FamilyHistoryMapper;
import com.example.datacollection_microservice.repository.FamilyHistoryRepository;
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
public class FamilyHistoryService {
    private final FamilyHistoryRepository familyHistoryRepository;
    private final PatientRepository patientRepository;
    private final FamilyHistoryMapper familyHistoryMapper;
    private final static Logger LOGGER = LoggerFactory.getLogger(FamilyHistoryService.class);

    @Transactional
    public FamilyHistory saveEntry(FamilyHistoryDTO familyHistoryDTO){
        UUID id = familyHistoryDTO.getPatientId();
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if(!patientOptional.isPresent()){
            LOGGER.error("Patient with id {} was not found", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + "with id " + id);
        }
        FamilyHistory newEntry = familyHistoryMapper.dtoToModel(familyHistoryDTO);
        newEntry.setPatient(patientOptional.get());
        newEntry = familyHistoryRepository.save(newEntry);
        return newEntry;
    }

    @Transactional
    public List<GetFamilyHistoryDTO> getFamilyHistory(UUID id){
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if(!patientOptional.isPresent()){
            LOGGER.error("Patient with id {} was not found", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + "with id " + id);
        }
        List<FamilyHistory> familyHistoryList = familyHistoryRepository.findAll();
        if(familyHistoryList.isEmpty()){
            return new ArrayList<>();
        }
        List<GetFamilyHistoryDTO> familyHistoryDTOList = new ArrayList<>();
        for(FamilyHistory familyHistory: familyHistoryList){
            if(familyHistory.getPatient().getId().equals(id)) {
                GetFamilyHistoryDTO familyHistoryDTO = familyHistoryMapper.modelToGetDto(familyHistory);
                familyHistoryDTOList.add(familyHistoryDTO);
            }
        }
        return familyHistoryDTOList;
    }

    public void deleteEntry(UUID id){
        Optional<FamilyHistory> familyHistoryEntryOptional = familyHistoryRepository.findById(id);
        if(!familyHistoryEntryOptional.isPresent()){
            LOGGER.error("Entry with id {} was not found", id);
            throw new ResourceNotFoundException(FamilyHistory.class.getSimpleName() + "with id " + id);
        }
        familyHistoryRepository.delete(familyHistoryEntryOptional.get());
    }

    @Transactional
    public GetFamilyHistoryDTO update(GetFamilyHistoryDTO familyHistoryDTO){
        Optional<FamilyHistory> familyHistoryOptional = familyHistoryRepository.findById(familyHistoryDTO.getId());
        if(!familyHistoryOptional.isPresent()){
            LOGGER.error("Family history entry with id {} was not found", familyHistoryDTO.getId());
            throw new ResourceNotFoundException(FamilyHistory.class.getSimpleName() + "with id " + familyHistoryDTO.getId());
        }
        System.out.println(familyHistoryDTO);
        FamilyHistory entryToUpdate = familyHistoryOptional.get();
        entryToUpdate.setRelationship(familyHistoryDTO.getRelationship());
        entryToUpdate.setGender(familyHistoryDTO.getGender());
        entryToUpdate.setName(familyHistoryDTO.getName());
        entryToUpdate.setCauseOfDeath(familyHistoryDTO.getCauseOfDeath());
        entryToUpdate.setAgeOfDeath(familyHistoryDTO.getAgeOfDeath());
        entryToUpdate.setTypeOfHeartDisease(familyHistoryDTO.getTypeOfHeartDisease());
        if(familyHistoryDTO.getHeartDisease().equals("Y")){
            entryToUpdate.setHeartDisease(true);
        }
        else{
            entryToUpdate.setHeartDisease(false);
        }
        entryToUpdate.setCurrentAge(familyHistoryDTO.getCurrentAge());

        entryToUpdate = familyHistoryRepository.save(entryToUpdate);
        GetFamilyHistoryDTO entryToUpdateDTO = familyHistoryMapper.modelToGetDto(entryToUpdate);
        return entryToUpdateDTO;
    }
}

