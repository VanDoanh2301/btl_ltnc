package com.example.Ltnc.dto;

import com.example.Ltnc.model.domain.Hospital;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

    private Long doctorId;

    private String name;

    private String avatar;

    private String email;

    private String phone;

    private String speciality;

    private Hospital hospital;
}
