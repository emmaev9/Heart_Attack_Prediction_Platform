package com.example.datacollection_microservice.controller;

import com.example.datacollection_microservice.dto.FamilyHistoryDTO;
import com.example.datacollection_microservice.dto.GetFamilyHistoryDTO;
import com.example.datacollection_microservice.dto.MessageResponse;
import com.example.datacollection_microservice.entity.FamilyHistory;
import com.example.datacollection_microservice.service.FamilyHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value="/familyHistory")
public class FamilyHistoryController {

    private final FamilyHistoryService familyHistoryService;

    @PostMapping()
    public ResponseEntity<?> saveEntry(@RequestBody FamilyHistoryDTO familyHistoryDTO){
        FamilyHistory familyHistory = familyHistoryService.saveEntry(familyHistoryDTO);
        return ResponseEntity.ok(familyHistory);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getFamilyHistoryForPatient(@PathVariable("id") UUID id){
        System.out.println("Patient id: " + id);
        List<GetFamilyHistoryDTO> familyHistoryDTOList = familyHistoryService.getFamilyHistory(id);
        return ResponseEntity.ok(familyHistoryDTOList);
    }
    @PutMapping()
    public ResponseEntity<?> updateFamilyHistoryEntry(@RequestBody GetFamilyHistoryDTO familyHistoryDTO){
        GetFamilyHistoryDTO updatedEntry = familyHistoryService.update(familyHistoryDTO);
        return ResponseEntity.ok(updatedEntry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFamilyHistoryEntry(@PathVariable("id") UUID id){
        familyHistoryService.deleteEntry(id);
        return ResponseEntity.ok(new MessageResponse("Family history entry deleted"));
    }

}
