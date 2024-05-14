package com.example.datacollection_microservice.controller;

import com.example.datacollection_microservice.dto.DoctorDTO;
import com.example.datacollection_microservice.dto.RegisterVisitDTO;
import com.example.datacollection_microservice.dto.VisitDTO;
import com.example.datacollection_microservice.entity.Visit;
import com.example.datacollection_microservice.service.DoctorService;
import com.example.datacollection_microservice.service.FileStorageService;
import com.example.datacollection_microservice.service.VisitService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value="/visit")
public class VisitController {
    private final VisitService visitService;
    private final DoctorService doctorService;
    private final FileStorageService fileStorageService;

    @PostMapping()
    public ResponseEntity<?> saveVisit(@RequestBody RegisterVisitDTO registerVisitDTO){
        Visit visit = visitService.saveVisit(registerVisitDTO);
        return ResponseEntity.ok(visit);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> handleUploadFile(@RequestParam("file") MultipartFile file){
        String fileUrl = fileStorageService.storeFile(file);
        return ResponseEntity.ok(fileUrl);
    }

    @PutMapping()
    public ResponseEntity<?> updateVisit(@RequestBody VisitDTO visitDTO){
        return ResponseEntity.ok(visitService.updateVisit(visitDTO));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<?> getAllPatientVisits(@PathVariable("id") UUID id){
        return ResponseEntity.ok(visitService.getPatientVisits(id));
    }

    @GetMapping("/visit/{id}")
    public ResponseEntity<?> getVisit(@PathVariable("id") UUID id){
        return ResponseEntity.ok(visitService.getVisitById(id));
    }
    @GetMapping("/latestVisit/{id}")
    public ResponseEntity<?> getLatestVisit(@PathVariable("id") String id){
        UUID newId = UUID.fromString(id);
        return ResponseEntity.ok(visitService.getLatestVisitOfPatient(newId));
    }
    @GetMapping("/doctors")
    public ResponseEntity<?> getAllDoctors(){
        List<DoctorDTO> doctorDTOList = doctorService.getDoctors();
        return ResponseEntity.ok(doctorDTOList);
    }
}
