package com.example.Ltnc.model.repository;

import com.example.Ltnc.model.domain.Doctor;
import com.example.Ltnc.model.domain.Patient;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
   public  boolean  existsPatientByPhone(Long phone);
   public boolean existsPatientByIdCard(String idCard);

   Patient findByPatientId(Long id);

   Patient findByEmailAndPassword(String email, String password);
}
