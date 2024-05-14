package com.example.datacollection_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardiacEvaluationDTO {
    private UUID visitId;
    private String restecg;
    private float oldpeak;
    private Integer thalach;
    private String slope;
    private String exang;
    private String thal;
    private Integer fbs;
    private Integer chol;
    private Integer ca;
}
