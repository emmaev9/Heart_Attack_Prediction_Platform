package com.example.datacollection_microservice.dto;


import com.example.datacollection_microservice.entity.Visit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationDTO {
    private UUID id;
    private UUID visitId;
    private String restecg;
    private Integer oldpeak;
    private Integer thalach;
    private String slope;
    private String exang;
    private Integer chol;
    private Integer fbs;
    private Integer ca;
    private String thal;
    private String trestbps;
}
