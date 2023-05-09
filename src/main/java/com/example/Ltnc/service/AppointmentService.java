package com.example.Ltnc.service;

import com.example.Ltnc.model.domain.Appointment;
import com.example.Ltnc.model.domain.Doctor;
import com.example.Ltnc.model.domain.Patient;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public interface AppointmentService {
    List<Appointment> findByPatient(Patient patient);

    List<Appointment> findByDoctor(Doctor doctor);

    void flush();

    <S extends Appointment> S saveAndFlush(S entity);

    <S extends Appointment> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<Appointment> entities);

    void deleteAllInBatch(Iterable<Appointment> entities);

    void deleteAllByIdInBatch(Iterable<Long> longs);

    void deleteAllInBatch();

    @Deprecated
    Appointment getOne(Long aLong);

    @Deprecated
    Appointment getById(Long aLong);

    Appointment getReferenceById(Long aLong);

    <S extends Appointment> List<S> findAll(Example<S> example);

    <S extends Appointment> List<S> findAll(Example<S> example, Sort sort);

    <S extends Appointment> List<S> saveAll(Iterable<S> entities);

    List<Appointment> findAll();

    List<Appointment> findAllById(Iterable<Long> longs);

    <S extends Appointment> S save(S entity);

    Optional<Appointment> findById(Long aLong);

    boolean existsById(Long aLong);

    long count();

    void deleteById(Long aLong);

    void delete(Appointment entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends Appointment> entities);

    void deleteAll();

    List<Appointment> findAll(Sort sort);

    Page<Appointment> findAll(Pageable pageable);

    <S extends Appointment> Optional<S> findOne(Example<S> example);

    <S extends Appointment> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Appointment> long count(Example<S> example);

    <S extends Appointment> boolean exists(Example<S> example);

    <S extends Appointment, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    List<Appointment> findByPatientId(Long patientId);
}
