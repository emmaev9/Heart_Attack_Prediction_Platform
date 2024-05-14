package com.example.datacollection_microservice.controller;

import com.example.datacollection_microservice.dto.MessageResponse;
import com.example.datacollection_microservice.dto.PatientDTO;
import com.example.datacollection_microservice.dto.PatientEditDTO;
import com.example.datacollection_microservice.dto.PatientRegisterDTO;
import com.example.datacollection_microservice.entity.Patient;
import com.example.datacollection_microservice.service.PatientService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/patient")
public class PatientController {
    private final PatientService patientService;

    @PostMapping()
    public ResponseEntity<?> registerPatient(@RequestBody PatientRegisterDTO patientRegisterDTO){
        PatientDTO patient = patientService.savePatient(patientRegisterDTO);
        System.out.println("REgister patient: " + patientRegisterDTO.toString());
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/patients")
    public ResponseEntity<List<PatientDTO>> getAllPatients(){
        return ResponseEntity.ok(patientService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getPatient(@PathVariable("id") UUID id){
        PatientDTO foundPatient = patientService.findPatient(id);
        if(foundPatient == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Patient with id " + id + "was not found"));
        }
        return ResponseEntity.ok(foundPatient);
    }
    @PutMapping("/update")
    public ResponseEntity<?> editPatient(@RequestBody PatientEditDTO patientEditDTO){
        System.out.println("Se face update la pacient");
        Patient editedPatient = patientService.updatePatient(patientEditDTO);
        return ResponseEntity.ok(editedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable("id") UUID id){
        System.out.println("SE sterge pacientul");
        PatientDTO foundPatient = patientService.findPatient(id);
        if(foundPatient == null){
            return ResponseEntity.badRequest().body(new MessageResponse("Patient with id " + id + "was not found"));
        }
        return ResponseEntity.ok(patientService.deletePatient(id));
    }

}
