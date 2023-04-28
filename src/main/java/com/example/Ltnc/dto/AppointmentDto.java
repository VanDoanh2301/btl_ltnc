package com.example.Ltnc.dto;

import com.example.Ltnc.model.domain.Doctor;
import com.example.Ltnc.model.domain.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {

    private Long id;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private String note;

    private Patient patient;

    private Doctor doctor;



}
