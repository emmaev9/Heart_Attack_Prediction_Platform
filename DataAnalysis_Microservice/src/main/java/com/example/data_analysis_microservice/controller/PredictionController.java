package com.example.data_analysis_microservice.controller;

import com.example.data_analysis_microservice.dto.RawInputDTO;
import com.example.data_analysis_microservice.service.PredictionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value="/prediction")
public class PredictionController {
    private final PredictionService predictionService;

    @PostMapping("/predict")
    public ResponseEntity<?> getPredictionOfModel(@RequestBody RawInputDTO predictionInput){
        String prediction = predictionService.getPrediction(predictionInput);
        System.out.println("Prediction: " + prediction);
        return ResponseEntity.ok(prediction);
    }


}
