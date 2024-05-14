package com.example.data_analysis_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModelDataDTO {
    private Integer restecg; //nominal
    private Integer oldpeak;
    private Integer thalach;
    private Integer slope;  //nominal
    private Integer exang; //nominal
    private Integer thal;  //nominal
    private Integer fbs; //nominal
    private Integer chol;
    private Integer ca;  //nominal
    private Integer age;
    private Integer gender; //nominal
    private Integer cp;  //nominal
    private Integer trestbps;
}
