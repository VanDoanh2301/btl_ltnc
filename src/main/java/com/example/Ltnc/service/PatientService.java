package com.example.Ltnc.service;

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
public interface PatientService {
    void flush();

    <S extends Patient> S saveAndFlush(S entity);

    <S extends Patient> List<S> saveAllAndFlush(Iterable<S> entities);

    @Deprecated
    void deleteInBatch(Iterable<Patient> entities);

    void deleteAllByIdInBatch(Iterable<Long> longs);

    void deleteAllInBatch();

    @Deprecated
    Patient getOne(Long aLong);

    @Deprecated
    Patient getById(Long aLong);

    Patient getReferenceById(Long aLong);

    <S extends Patient> List<S> findAll(Example<S> example);

    <S extends Patient> List<S> findAll(Example<S> example, Sort sort);

    <S extends Patient> List<S> saveAll(Iterable<S> entities);

    List<Patient> findAll();

    List<Patient> findAllById(Iterable<Long> longs);

    <S extends Patient> S save(S entity);

    Optional<Patient> findById(Long aLong);

    boolean existsById(Long aLong);

    long count();

    void deleteById(Long aLong);

    void delete(Patient entity);

    void deleteAllById(Iterable<? extends Long> longs);

    void deleteAll(Iterable<? extends Patient> entities);

    void deleteAll();

    List<Patient> findAll(Sort sort);

    Page<Patient> findAll(Pageable pageable);

    <S extends Patient> Optional<S> findOne(Example<S> example);

    <S extends Patient> Page<S> findAll(Example<S> example, Pageable pageable);

    <S extends Patient> long count(Example<S> example);

    <S extends Patient> boolean exists(Example<S> example);

    <S extends Patient, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);

    boolean existsPatientByPhone(Long phone);

    boolean existsPatientByIdCard(String idCard);

    Patient findByPatientId(Long id);
}
