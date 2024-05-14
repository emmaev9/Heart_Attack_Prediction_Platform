package com.example.datacollection_microservice.controller;

import com.example.datacollection_microservice.dto.DoctorDTO;
import com.example.datacollection_microservice.dto.MessageResponse;
import com.example.datacollection_microservice.dto.PatientDTO;
import com.example.datacollection_microservice.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value="/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/insert")
    public ResponseEntity<?> saveDoctor(@RequestBody DoctorDTO doctorDTO){
        doctorService.saveDoctor(doctorDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateDoctor(@RequestBody DoctorDTO doctorDTO){
        DoctorDTO updatedDoctor = doctorService.updateDoctor(doctorDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFamilyHistoryEntry(@PathVariable("id") UUID id){
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{doctorId}/{patientId}")
    public ResponseEntity<?> updateDoctorPatientRelationship(@PathVariable("doctorId") UUID doctorId,
                                                             @PathVariable("patientId") UUID patientId
                                                             ){
        System.out.println("Doctor Id: " + doctorId);
        System.out.println("Patient id: " + patientId);
        doctorService.setPatientToDoctor(doctorId, patientId);
        return ResponseEntity.ok(new MessageResponse("The patient was set to the doctor"));
    }
    @GetMapping("/getByPatient/{id}")
    public ResponseEntity<?> getDoctorByPatient(@PathVariable("id") UUID id){
        DoctorDTO doctorDTO = doctorService.getDoctorForPatient(id);
        return ResponseEntity.ok(doctorDTO);
    }

    @GetMapping("/allPatientsOfDoctor/{id}")
    public ResponseEntity<?> getAlPatientsOfDoctor(@PathVariable("id") UUID id){
        List<PatientDTO> patientDTOList = doctorService.getAllPatientsOfDoctor(id);
        return ResponseEntity.ok(patientDTOList);
    }
}
