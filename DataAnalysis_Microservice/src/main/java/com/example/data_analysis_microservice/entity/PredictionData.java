package com.example.data_analysis_microservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="prediction_data")
public class PredictionData {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false)
    private UUID visitId;

    @Column(nullable = false)
    private UUID patientId;

    @Column(nullable = false)
    private Integer restecg;

    @Column(nullable = false)
    private Integer oldpeak;

    @Column(nullable = false)
    private Integer thalach;

    @Column(nullable = false)
    private Integer slope;

    @Column(nullable = false)
    private Integer exang;

    @Column(nullable = false)
    private Integer thal;

    @Column(nullable = false)
    private Integer fbs;

    @Column(nullable = false)
    private Integer chol;

    @Column(nullable = false)
    private Integer ca;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Integer gender;

    @Column(nullable = false)
    private Integer cp;

    @Column(nullable = false)
    private Integer trestbps;

    @Column(nullable = true)
    private Integer target;
}
