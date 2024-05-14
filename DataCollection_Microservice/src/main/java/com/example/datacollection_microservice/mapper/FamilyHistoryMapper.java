package com.example.datacollection_microservice.mapper;


import com.example.datacollection_microservice.dto.FamilyHistoryDTO;
import com.example.datacollection_microservice.dto.GetFamilyHistoryDTO;
import com.example.datacollection_microservice.entity.FamilyHistory;
import org.springframework.stereotype.Component;

@Component
public class FamilyHistoryMapper {

    public FamilyHistoryDTO modelToDto(FamilyHistory familyHistory){
        FamilyHistoryDTO familyHistoryDTO = new FamilyHistoryDTO();
        familyHistoryDTO.setPatientId(familyHistory.getPatient().getId());
        familyHistoryDTO.setName(familyHistory.getName());
        familyHistoryDTO.setGender(familyHistory.getGender());
        familyHistoryDTO.setRelationship(familyHistory.getRelationship());
        if(familyHistory.isHeartDisease()){
            familyHistoryDTO.setHeartDisease("Y");
        }else{
            familyHistoryDTO.setHeartDisease("N");
        }
        familyHistoryDTO.setTypeOfHeartDisease(familyHistory.getTypeOfHeartDisease());
        familyHistoryDTO.setAgeOfDeath(familyHistory.getAgeOfDeath());
        familyHistoryDTO.setCauseOfDeath(familyHistory.getCauseOfDeath());
        familyHistoryDTO.setCurrentAge(familyHistory.getCurrentAge());
        return familyHistoryDTO;
    }

    public FamilyHistory dtoToModel(FamilyHistoryDTO familyHistoryDTO){
        FamilyHistory familyHistory = new FamilyHistory();
        familyHistory.setName(familyHistoryDTO.getName());
        familyHistory.setGender(familyHistoryDTO.getGender());
        familyHistory.setRelationship(familyHistoryDTO.getRelationship());
        if(familyHistoryDTO.equals("Y")){
            familyHistory.setHeartDisease(true);
        }else{
            familyHistory.setHeartDisease(false);
        }
        familyHistory.setTypeOfHeartDisease(familyHistoryDTO.getTypeOfHeartDisease());
        familyHistory.setAgeOfDeath(familyHistoryDTO.getAgeOfDeath());
        familyHistory.setCauseOfDeath(familyHistoryDTO.getCauseOfDeath());
        familyHistory.setCurrentAge(familyHistoryDTO.getCurrentAge());
        return familyHistory;
    }

    public GetFamilyHistoryDTO modelToGetDto(FamilyHistory familyHistory){
        GetFamilyHistoryDTO familyHistoryDTO = new GetFamilyHistoryDTO();
        familyHistoryDTO.setId(familyHistory.getId());
        familyHistoryDTO.setPatientId(familyHistory.getPatient().getId());
        familyHistoryDTO.setName(familyHistory.getName());
        familyHistoryDTO.setGender(familyHistory.getGender());
        familyHistoryDTO.setRelationship(familyHistory.getRelationship());
        if(familyHistory.isHeartDisease()){
            familyHistoryDTO.setHeartDisease("Y");
        }else{
            familyHistoryDTO.setHeartDisease("N");
        }
        familyHistoryDTO.setTypeOfHeartDisease(familyHistory.getTypeOfHeartDisease());
        familyHistoryDTO.setAgeOfDeath(familyHistory.getAgeOfDeath());
        familyHistoryDTO.setCauseOfDeath(familyHistory.getCauseOfDeath());
        familyHistoryDTO.setCurrentAge(familyHistory.getCurrentAge());
        return familyHistoryDTO;
    }
}
