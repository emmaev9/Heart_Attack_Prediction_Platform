package com.example.datacollection_microservice.mapper;

import com.example.datacollection_microservice.dto.RegisterVisitDTO;
import com.example.datacollection_microservice.dto.VisitDTO;
import com.example.datacollection_microservice.entity.Patient;
import com.example.datacollection_microservice.entity.Visit;
import org.springframework.stereotype.Component;

@Component
public class VisitMapper {

    public Visit registerDtoToModel(RegisterVisitDTO registerVisitDTO, Patient patient){
        Visit visit = new Visit();
        visit.setVisitDate(registerVisitDTO.getVisitDate());
        visit.setPatient(patient);
        visit.setVisitReason(registerVisitDTO.getVisitReason());
        visit.setTrestbps(registerVisitDTO.getTrestbps());
        visit.setChestPainType(registerVisitDTO.getChestPainType());
        visit.setBloodTestPdfUrl(registerVisitDTO.getBloodTestPdfUrl());
        visit.setAfterExerciseECGUrl(registerVisitDTO.getAfterExerciseECGUrl());
        visit.setBeforeExerciseECGUrl(registerVisitDTO.getBeforeExerciseECGUrl());
        visit.setDuringExerciseECGUrl(registerVisitDTO.getDuringExerciseECGUrl());
        visit.setCoronaryAngiographyUrl(registerVisitDTO.getCoronaryAngiographyUrl());
        return visit;
    }

    public Visit dtoToModel(VisitDTO visitDTO){
        Visit visit = new Visit();
        visit.setVisitDate(visitDTO.getVisitDate());
        visit.setId(visitDTO.getId());
        visit.setVisitReason(visitDTO.getVisitReason());
        visit.setTrestbps(visitDTO.getTrestbps());
        visit.setChestPainType(visitDTO.getChestPainType());
        visit.setBloodTestPdfUrl(visitDTO.getBloodTestPdfUrl());
        visit.setAfterExerciseECGUrl(visitDTO.getAfterExerciseECGUrl());
        visit.setBeforeExerciseECGUrl(visitDTO.getBeforeExerciseECGUrl());
        visit.setDuringExerciseECGUrl(visitDTO.getDuringExerciseECGUrl());
        visit.setCoronaryAngiographyUrl(visitDTO.getCoronaryAngiographyUrl());
        return visit;
    }

    public VisitDTO modelToDTO(Visit visit){
        VisitDTO visitDTO = new VisitDTO();
        visitDTO.setVisitDate(visit.getVisitDate());
        visitDTO.setId(visit.getId());
        visitDTO.setPatientId(visit.getPatient().getId());
        visitDTO.setVisitReason(visit.getVisitReason());
        visitDTO.setChestPainType(visit.getChestPainType());
        visitDTO.setTrestbps(visit.getTrestbps());
        visitDTO.setBloodTestPdfUrl(visit.getBloodTestPdfUrl());
        visitDTO.setAfterExerciseECGUrl(visit.getAfterExerciseECGUrl());
        visitDTO.setBeforeExerciseECGUrl(visit.getBeforeExerciseECGUrl());
        visitDTO.setDuringExerciseECGUrl(visit.getDuringExerciseECGUrl());
        visitDTO.setCoronaryAngiographyUrl(visit.getCoronaryAngiographyUrl());
        return visitDTO;
    }
}
