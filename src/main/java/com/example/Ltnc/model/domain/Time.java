package com.example.Ltnc.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "time")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int timeId;

    @Column(name = "period", nullable = false, length = 225)
    private String period;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"time", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    public Time() {
    }

    public Time(int timeId, String period, Doctor doctor) {
        this.timeId = timeId;
        this.period = period;
        this.doctor = doctor;
    }

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
