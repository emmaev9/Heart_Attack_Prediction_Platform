package com.example.datacollection_microservice.repository;

import com.example.datacollection_microservice.entity.FamilyHistory;
import com.example.datacollection_microservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FamilyHistoryRepository extends JpaRepository<FamilyHistory, UUID> {
   // List<FamilyHistory> findAllByPatient(Patient patient);
}
