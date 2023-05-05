package com.example.Ltnc.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@Entity
@Table(name = "appointment")
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointment_date", nullable = false)
    private String appointmentDate;

    @Column(name = "period", nullable = false, length = 225)
    private String period;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JsonIgnoreProperties(value = {"Appointment", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JsonIgnoreProperties(value = {"Appointment", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;



}
