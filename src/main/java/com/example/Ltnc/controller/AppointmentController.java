package com.example.Ltnc.controller;

import com.example.Ltnc.model.domain.Appointment;
import com.example.Ltnc.model.domain.Doctor;
import com.example.Ltnc.model.domain.Patient;
import com.example.Ltnc.service.AppointmentService;
import com.example.Ltnc.service.DoctorService;
import com.example.Ltnc.service.PatientService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import  com.example.Ltnc.dto.AppointmentDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth/")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @PostMapping("/newAppointment")
    public ResponseEntity<?> newAppointment(@RequestBody AppointmentDto appointmentDto) {
        Doctor doctor = doctorService.findByDoctorId(appointmentDto.getDoctorId());
        Patient patient = patientService.findByPatientId(appointmentDto.getPatientId());
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDto.getId());
        appointment.setPeriod(appointmentDto.getPeriod());
        appointment.setNote(appointmentDto.getNote());
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setPatient(patient);
        appointmentService.save(appointment);
        return ResponseEntity.ok("successfully");
    }
    @GetMapping("/appointment/{patientId}")
    public ResponseEntity<?> getAppointmentById(@PathVariable(name = "patientId") Long patientId) {
        List<Appointment> appointments = appointmentService.findByPatientId(patientId);
        if (appointments.isEmpty()) {
            return ResponseEntity.ok("Null");
        }
        return ResponseEntity.ok(appointments);
    }
    @GetMapping("/appointments/{id}")
    public ResponseEntity<?> findAppointmentById(@PathVariable(name = "id") Long id) {
       Optional<Appointment> appointments = appointmentService.findById(id);
        if (appointments.isEmpty()) {
            return ResponseEntity.ok("null");
        }

        return ResponseEntity.ok(appointments);
    }

}
