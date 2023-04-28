package com.example.Ltnc.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {

    private Long patientId;

    private String name;

    private String email;

    private String phone;

    private String idCard;

    private String address;

    private Date birthYear;

    private String password;


}
