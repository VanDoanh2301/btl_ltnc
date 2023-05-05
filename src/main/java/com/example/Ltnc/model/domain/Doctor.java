package com.example.Ltnc.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Entity
@Table(name = "doctor")
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "avatar", nullable = false)
    private String avatar;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone")
    private String phone;


    @Column(name = "day")
    private String day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"doctors", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "specialistId")
    private Specialist specialist;

    @OneToMany(mappedBy = "doctor")
    private Collection<Time>  times;

    @OneToMany(mappedBy = "doctor")
    private Collection<Appointment>  appointments;

}
