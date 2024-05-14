package com.example.datacollection_microservice.controller;

import com.example.datacollection_microservice.dto.CardiacEvaluationDTO;
import com.example.datacollection_microservice.dto.MessageResponse;
import com.example.datacollection_microservice.repository.MedicalEvaluationRepository;
import com.example.datacollection_microservice.service.MedicalEvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/evaluation")
public class MedicalEvaluationController {

    private final MedicalEvaluationService medicalEvaluationService;

    @PutMapping("/cardiacEvaluation")
    public ResponseEntity<?> updateEvaluation(@RequestBody CardiacEvaluationDTO cardiacEvaluationDTO){
        medicalEvaluationService.updateCardiacEvaluation(cardiacEvaluationDTO);
        return ResponseEntity.ok(new MessageResponse("Cardiac evaluation saved"));
    }

    @GetMapping("/cardiacEvaluation/{id}")
    public ResponseEntity<?> getCardiacEvaluation(@PathVariable("id") UUID id){
        return ResponseEntity.ok(medicalEvaluationService.getCardiacEvaluation(id));
    }
}
