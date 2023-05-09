package com.example.Ltnc.service.serviceImpl;

import com.example.Ltnc.model.domain.Patient;
import com.example.Ltnc.model.repository.PatientRepository;
import com.example.Ltnc.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepo;

    @Override
    public void flush() {
        patientRepo.flush();
    }

    @Override
    public <S extends Patient> S saveAndFlush(S entity) {
        return patientRepo.saveAndFlush(entity);
    }

    @Override
    public <S extends Patient> List<S> saveAllAndFlush(Iterable<S> entities) {
        return patientRepo.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Patient> entities) {
        patientRepo.deleteInBatch(entities);
    }

    public void deleteAllInBatch(Iterable<Patient> entities) {
        patientRepo.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        patientRepo.deleteAllByIdInBatch(longs);
    }

    @Override
    public void deleteAllInBatch() {
        patientRepo.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public Patient getOne(Long aLong) {
        return patientRepo.getOne(aLong);
    }

    @Override
    @Deprecated
    public Patient getById(Long aLong) {
        return patientRepo.getById(aLong);
    }

    @Override
    public Patient getReferenceById(Long aLong) {
        return patientRepo.getReferenceById(aLong);
    }

    @Override
    public <S extends Patient> List<S> findAll(Example<S> example) {
        return patientRepo.findAll(example);
    }

    @Override
    public <S extends Patient> List<S> findAll(Example<S> example, Sort sort) {
        return patientRepo.findAll(example, sort);
    }

    @Override
    public <S extends Patient> List<S> saveAll(Iterable<S> entities) {
        return patientRepo.saveAll(entities);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepo.findAll();
    }

    @Override
    public List<Patient> findAllById(Iterable<Long> longs) {
        return patientRepo.findAllById(longs);
    }

    @Override
    public <S extends Patient> S save(S entity) {
        return patientRepo.save(entity);
    }

    @Override
    public Optional<Patient> findById(Long aLong) {
        return patientRepo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return patientRepo.existsById(aLong);
    }

    @Override
    public long count() {
        return patientRepo.count();
    }

    @Override
    public void deleteById(Long aLong) {
        patientRepo.deleteById(aLong);
    }

    @Override
    public void delete(Patient entity) {
        patientRepo.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        patientRepo.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Patient> entities) {
        patientRepo.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        patientRepo.deleteAll();
    }

    @Override
    public List<Patient> findAll(Sort sort) {
        return patientRepo.findAll(sort);
    }

    @Override
    public Page<Patient> findAll(Pageable pageable) {
        return patientRepo.findAll(pageable);
    }

    @Override
    public <S extends Patient> Optional<S> findOne(Example<S> example) {
        return patientRepo.findOne(example);
    }

    @Override
    public <S extends Patient> Page<S> findAll(Example<S> example, Pageable pageable) {
        return patientRepo.findAll(example, pageable);
    }

    @Override
    public <S extends Patient> long count(Example<S> example) {
        return patientRepo.count(example);
    }

    @Override
    public <S extends Patient> boolean exists(Example<S> example) {
        return patientRepo.exists(example);
    }

    @Override
    public <S extends Patient, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return patientRepo.findBy(example, queryFunction);
    }
    //---------------------------------------------------------------------------------------//


    //exists phone
    @Override
    public boolean existsPatientByPhone(Long phone) {
        return patientRepo.existsPatientByPhone(phone);
    }

    //exist card
    @Override
    public boolean existsPatientByIdCard(String idCard) {
        return patientRepo.existsPatientByIdCard(idCard);
    }

    @Override
    public Patient findByPatientId(Long id) {
        return patientRepo.findByPatientId(id);
    }

    @Override
    public Patient findByEmailAndPassword(String email, String password) {
        return patientRepo.findByEmailAndPassword(email, password);
    }

    @Override
    public Patient findByEmail(String email) {
        return patientRepo.findByEmail(email);
    }
}
