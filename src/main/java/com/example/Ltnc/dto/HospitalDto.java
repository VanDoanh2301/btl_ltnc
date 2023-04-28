package com.example.Ltnc.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hospital")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hospitalId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;
}

