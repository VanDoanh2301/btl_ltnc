package com.example.Ltnc.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Table(name = "appointment")
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

    public Appointment() {
    }

    public Appointment(Long id, String appointmentDate, String period, String note, Patient patient, Doctor doctor) {
        this.id = id;
        this.appointmentDate = appointmentDate;
        this.period = period;
        this.note = note;
        this.patient = patient;
        this.doctor = doctor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPatient() {
        return patient.getName();
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDoctor() {
        return doctor.getName();
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
