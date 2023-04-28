package com.example.Ltnc.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "address")
    private String address;

    @Column(name = "birth_year")
    @Temporal(TemporalType.DATE)
    private Date birthYear;

    @Column(name = "password")
    private String password;


}
