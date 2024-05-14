package com.example.datacollection_microservice.repository;

import com.example.datacollection_microservice.entity.HealthDetails;
import com.example.datacollection_microservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HealthDetailsRepository extends JpaRepository<HealthDetails, UUID> {
    Optional<HealthDetails> findByPatient_Id(UUID id);
}
