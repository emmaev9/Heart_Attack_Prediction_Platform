package com.example.datacollection_microservice.repository;

import com.example.datacollection_microservice.entity.MedicalEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicalEvaluationRepository extends JpaRepository<MedicalEvaluation, UUID> {
}
