package com.example.Ltnc.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "time")
@AllArgsConstructor
@NoArgsConstructor
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timeId;

    @Column(name = "period", nullable = false, length = 225)
    private String period;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"time", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;
}
