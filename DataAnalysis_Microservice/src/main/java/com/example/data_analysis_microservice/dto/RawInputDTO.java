package com.example.data_analysis_microservice.dto;

import lombok.*;

import java.util.UUID;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RawInputDTO {
    private UUID visitId;
    private UUID patientId;
    private String restecg;
    private Integer oldpeak;
    private Integer thalach;
    private String slope;
    private String exang;
    private String thal;
    private Integer fbs;
    private Integer chol;
    private Integer ca;
    private Integer age;
    private String gender;
    private String cp;
    private Integer trestbps;
}
