package com.example.Ltnc.model.repository;

import com.example.Ltnc.model.domain.Doctor;
import com.example.Ltnc.model.domain.Time;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
    Page<Time> findByPeriodContaining(String period, Pageable pageable);
}
