package com.example.datacollection_microservice.service;

import com.example.datacollection_microservice.dto.CardiacEvaluationDTO;
import com.example.datacollection_microservice.entity.MedicalEvaluation;
import com.example.datacollection_microservice.entity.Visit;
import com.example.datacollection_microservice.exception.ResourceNotFoundException;
import com.example.datacollection_microservice.repository.MedicalEvaluationRepository;
import com.example.datacollection_microservice.repository.VisitRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MedicalEvaluationService {

    private final MedicalEvaluationRepository medicalEvaluationRepository;
    private final VisitRepository visitRepository;
    private final static Logger LOGGER = LoggerFactory.getLogger(MedicalEvaluationService.class);

    public void updateCardiacEvaluation(CardiacEvaluationDTO cardiacEvaluationDTO){
        MedicalEvaluation existentMedicalEvaluation = findMedicalEvaluationByVisitId(cardiacEvaluationDTO.getVisitId());
        Optional<Visit> visitOptional = visitRepository.findById(cardiacEvaluationDTO.getVisitId());
        if(!visitOptional.isPresent()){
            LOGGER.error("Visit with id {} was not found", cardiacEvaluationDTO.getVisitId());
            throw new ResourceNotFoundException(Visit.class.getSimpleName() + "with id " + cardiacEvaluationDTO.getVisitId());
        }
        if(existentMedicalEvaluation!=null){
            existentMedicalEvaluation.setSlope(cardiacEvaluationDTO.getSlope());
            existentMedicalEvaluation.setRestecg(cardiacEvaluationDTO.getRestecg());
            existentMedicalEvaluation.setOldpeak(cardiacEvaluationDTO.getOldpeak());
            existentMedicalEvaluation.setExang(cardiacEvaluationDTO.getExang());
            existentMedicalEvaluation.setThalach(cardiacEvaluationDTO.getThalach());
            existentMedicalEvaluation.setVisit(visitOptional.get());
            existentMedicalEvaluation.setThal(cardiacEvaluationDTO.getThal());
            existentMedicalEvaluation.setCa(cardiacEvaluationDTO.getCa());
            existentMedicalEvaluation.setFbs(cardiacEvaluationDTO.getFbs());
            existentMedicalEvaluation.setVisit(existentMedicalEvaluation.getVisit());
            existentMedicalEvaluation.setChol(cardiacEvaluationDTO.getChol());

            medicalEvaluationRepository.save(existentMedicalEvaluation);
        }else{
            MedicalEvaluation medicalEvaluation = new MedicalEvaluation();
            medicalEvaluation.setSlope(cardiacEvaluationDTO.getSlope());
            medicalEvaluation.setRestecg(cardiacEvaluationDTO.getRestecg());
            medicalEvaluation.setOldpeak(cardiacEvaluationDTO.getOldpeak());
            medicalEvaluation.setExang(cardiacEvaluationDTO.getExang());
            medicalEvaluation.setThalach(cardiacEvaluationDTO.getThalach());
            medicalEvaluation.setThal(cardiacEvaluationDTO.getThal());
            medicalEvaluation.setCa(cardiacEvaluationDTO.getCa());
            medicalEvaluation.setFbs(cardiacEvaluationDTO.getFbs());
            medicalEvaluation.setChol(cardiacEvaluationDTO.getChol());
            medicalEvaluation.setVisit(visitOptional.get());

            medicalEvaluationRepository.save(medicalEvaluation);
        }
    }

    public MedicalEvaluation findMedicalEvaluationByVisitId(UUID visitId){
        List<MedicalEvaluation> medicalEvaluationList = medicalEvaluationRepository.findAll();
        if(medicalEvaluationList.isEmpty()){
            return null;
        }
        for(MedicalEvaluation medicalEvaluation: medicalEvaluationList){
            if(medicalEvaluation.getVisit().getId().equals(visitId)){
                return medicalEvaluation;
            }
        }
        return null;
    }

    public CardiacEvaluationDTO getCardiacEvaluation(UUID visitId){
        Optional<Visit> visitOptional = visitRepository.findById(visitId);
        if(!visitOptional.isPresent()){
            LOGGER.error("Visit with id {} was not found", visitId);
            throw new ResourceNotFoundException(Visit.class.getSimpleName() + "with id " + visitId);
        }
        MedicalEvaluation medicalEvaluation = findMedicalEvaluationByVisitId(visitId);
        if(medicalEvaluation!=null) {
            CardiacEvaluationDTO cardiacEvaluationDTO = new CardiacEvaluationDTO();
            cardiacEvaluationDTO.setExang(medicalEvaluation.getExang());
            cardiacEvaluationDTO.setOldpeak(medicalEvaluation.getOldpeak());
            cardiacEvaluationDTO.setSlope(medicalEvaluation.getSlope());
            cardiacEvaluationDTO.setThalach(medicalEvaluation.getThalach());
            cardiacEvaluationDTO.setRestecg(medicalEvaluation.getRestecg());
            cardiacEvaluationDTO.setThal(medicalEvaluation.getThal());
            cardiacEvaluationDTO.setChol(medicalEvaluation.getChol());
            cardiacEvaluationDTO.setFbs(medicalEvaluation.getFbs());
            cardiacEvaluationDTO.setCa(medicalEvaluation.getCa());

            cardiacEvaluationDTO.setVisitId(visitId);
            return cardiacEvaluationDTO;
        }
        return null;
    }
}
