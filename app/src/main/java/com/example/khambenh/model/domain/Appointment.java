package com.example.khambenh.model.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Appointment implements Serializable {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("appointmentDate")
    @Expose
    private String appointmentDate;

    @SerializedName("period")
    @Expose
    private String period;

    @SerializedName("note")
    @Expose
    private String note;

    private Long doctorId;
    private Long patientId;

    @SerializedName("patient")
    @Expose
    private Patient patient;

    @SerializedName("doctor")
    @Expose
    private Doctor doctor;


    public Appointment() {
    }

    public Appointment(String appointmentDate, String period, String note, Long doctorId, Long patientId) {
        this.appointmentDate = appointmentDate;
        this.period = period;
        this.note = note;
        this.doctorId = doctorId;
        this.patientId = patientId;
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

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
