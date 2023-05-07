package com.example.Ltnc;

import com.example.Ltnc.model.domain.Doctor;
import com.example.Ltnc.model.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LtncApplicationTests {
@Autowired
private DoctorRepository doctorRepo;
	@Test
	void contextLoads() {
		List<Doctor> doctors = doctorRepo.getDoctorByTimesAndAndAppointments("4:00", 1L);

	}

}
