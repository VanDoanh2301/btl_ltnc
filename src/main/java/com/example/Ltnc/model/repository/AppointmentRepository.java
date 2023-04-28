package com.example.Ltnc.model.repository;

import com.example.Ltnc.model.domain.Appointment;
import com.example.Ltnc.model.domain.Doctor;
import com.example.Ltnc.model.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatient(Patient patient);

    List<Appointment> findByDoctor(Doctor doctor);

    List<Appointment> findByAppointmentDate(LocalDate appointmentDate);

    List<Appointment> findByAppointmentTime(LocalTime appointmentTime);
}