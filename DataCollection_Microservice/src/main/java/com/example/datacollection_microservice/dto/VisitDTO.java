package com.example.datacollection_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class VisitDTO {
    private UUID id;
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
