package com.example.Ltnc.controller;

import com.example.Ltnc.dto.PatientDto;
import com.example.Ltnc.model.domain.Doctor;
import com.example.Ltnc.model.domain.Patient;
import com.example.Ltnc.service.PatientService;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class PatientController {

    @Autowired
    private PatientService patientService;

    //Register patient
    @PostMapping("/newPatient")
    public ResponseEntity<?> newPatient(@Valid @RequestBody PatientDto patientDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        if (patientService.existsPatientByPhone(patientDto.getPhone())) {
            return ResponseEntity.badRequest().body("Phone number already exists");
        }
        if (patientService.existsPatientByIdCard(patientDto.getIdCard())) {
            return ResponseEntity.badRequest().body("ID card number already exists");
        }
        if(!isValidEmail(patientDto.getEmail())) {
            return ResponseEntity.badRequest().body("Invalid email address");
        }
        if(StringUtils.isBlank(patientDto.getName())) {
            return ResponseEntity.badRequest().body("Name is required");
        }
        if(StringUtils.isBlank(String.valueOf(patientDto.getPhone()))) {
            return ResponseEntity.badRequest().body("Phone is required");
        }
        if(StringUtils.isBlank(patientDto.getIdCard())) {
            return ResponseEntity.badRequest().body("IdCard is required");
        }
        if (patientDto.getPassword().length() < 8) {
            return ResponseEntity.badRequest().body("Password phải từ 8 kí tự trở lên");
        }

        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDto, patient);
        patientService.save(patient);
        return ResponseEntity.ok("Patient created successfully");
    }

    private boolean isValidEmail(String email) {
        String regex = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    //Get patient by Id
    @GetMapping("/patients/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable(name = "id") Long id) {
        Optional<Patient> patient = patientService.findById(id);
        if (patient.isEmpty()) {
            return ResponseEntity.ok("Null");
        }
        return ResponseEntity.ok(patient);
    }


}
