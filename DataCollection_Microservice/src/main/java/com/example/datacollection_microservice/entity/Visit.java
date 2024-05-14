package com.example.datacollection_microservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="visit")
public class Visit {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private LocalDateTime visitDate;

    @Column(nullable = false)
    private String chestPainType;

    @Column(nullable = false)
    private String visitReason;

    @Column(nullable = true)
    private Integer trestbps;

    @Column(name = "before_exercise_ecg_url", nullable = true)
    private String beforeExerciseECGUrl;

    @Column(name = "during_exercise_ecg_url", nullable = true)
    private String duringExerciseECGUrl;

    @Column(name = "after_exercise_ecg_url", nullable = true)
    private String afterExerciseECGUrl;

    @Column(name = "blood_test_pdf_url", nullable = true)
    private String bloodTestPdfUrl;

    @Column(name = "coronary_angiography_url", nullable = true)
    private String coronaryAngiographyUrl;

    @OneToOne(mappedBy = "visit", cascade = CascadeType.ALL, orphanRemoval = true)
    private MedicalEvaluation medicalEvaluation;

}
