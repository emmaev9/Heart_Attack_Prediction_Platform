package com.example.datacollection_microservice.controller;

import com.example.datacollection_microservice.dto.HealthDetailsDTO;
import com.example.datacollection_microservice.dto.MessageResponse;
import com.example.datacollection_microservice.service.HealthDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
@RequestMapping(value="/patientDetails")
public class HealthDetailsController {
    private final HealthDetailsService healthDetailsService;

    @GetMapping("{id}")
    public ResponseEntity<?> getPatientHealthDetails(@PathVariable("id") UUID id){
        if(healthDetailsService.getHealthDetails(id)==null){
            return ResponseEntity.ok(new MessageResponse("No data"));
        }
        HealthDetailsDTO healthDetailsDTO = healthDetailsService.getHealthDetails(id);
        return ResponseEntity.ok(healthDetailsDTO);
    }

    @PostMapping()
    public ResponseEntity<?> saveChanges(@RequestBody HealthDetailsDTO healthDetailsDTO){
        System.out.println("Health details: ");
        System.out.println(healthDetailsDTO.toString());
        UUID id = healthDetailsService.saveDetails(healthDetailsDTO);
        System.out.println("ID: " + id);
        return ResponseEntity.ok(id);

    }
}
