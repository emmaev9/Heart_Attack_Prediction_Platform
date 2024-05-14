package com.example.datacollection_microservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name="medical_evaluation")
public class MedicalEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_id", nullable = false)
    private Visit visit;

    @Column(name="restecg", nullable = true)
    private String restecg;

    @Column(name="oldpeak", nullable = true)
    private float oldpeak;

    @Column(name="thalach", nullable = true)
    private Integer thalach;

    @Column(name="slope", nullable = true)
    private String slope;

    @Column(name="exang", nullable = true)
    private String exang;

    @Column(name="chol", nullable = true)
    private Integer chol;

    @Column(name="fbs", nullable = true)
    private Integer fbs;

    @Column(name="ca", nullable = true)
    private Integer ca;

    @Column(name="thal", nullable = true)
    private String thal;
}
