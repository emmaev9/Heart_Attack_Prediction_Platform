package com.example.datacollection_microservice.dto;

import com.example.datacollection_microservice.entity.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVisitDTO {
    private UUID patientId;
    private LocalDateTime visitDate;
    private String chestPainType;
    private UUID doctorId;
    private String visitReason;
    private Integer trestbps;
    private String beforeExerciseECGUrl;
    private String duringExerciseECGUrl;
    private String afterExerciseECGUrl;
    private String bloodTestPdfUrl;
    private String coronaryAngiographyUrl;
}
