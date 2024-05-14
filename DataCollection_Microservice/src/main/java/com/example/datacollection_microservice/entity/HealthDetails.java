package com.example.datacollection_microservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name="health_details")
public class HealthDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private String smokingStatus;

    @Column(nullable = false)
    private String alcoholConsumption;

    @Column(nullable = false)
    private String physicalActivityLevel;

    @Column(nullable = false)
    private String stressLevel;

    @Column(nullable = false)
    private String bodyMassIndex;

    @Column(nullable = false)
    private String diabetes;

    @Column(nullable = false)
    private String historyOfHD; //HD aka heart disease

    @Column(nullable = false)
    private String historyOfHA; //HA aka heart attack

    @Column(nullable = false)
    private String otherCondition;
}
