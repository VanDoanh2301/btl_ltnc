package com.example.Ltnc.service.serviceImpl;

import com.example.Ltnc.model.domain.Appointment;
import com.example.Ltnc.model.domain.Doctor;
import com.example.Ltnc.model.domain.Patient;
import com.example.Ltnc.model.repository.AppointmentRepository;
import com.example.Ltnc.service.AppointmentService;
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
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Override
    public List<Appointment> findByPatient(Patient patient) {
        return appointmentRepo.findByPatient(patient);
    }

    @Override
    public List<Appointment> findByDoctor(Doctor doctor) {
        return appointmentRepo.findByDoctor(doctor);
    }

    @Override
    public void flush() {
        appointmentRepo.flush();
    }

    @Override
    public <S extends Appointment> S saveAndFlush(S entity) {
        return appointmentRepo.saveAndFlush(entity);
    }

    @Override
    public <S extends Appointment> List<S> saveAllAndFlush(Iterable<S> entities) {
        return appointmentRepo.saveAllAndFlush(entities);
    }

    @Override
    @Deprecated
    public void deleteInBatch(Iterable<Appointment> entities) {
        appointmentRepo.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<Appointment> entities) {
        appointmentRepo.deleteAllInBatch(entities);
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        appointmentRepo.deleteAllByIdInBatch(longs);
    }

    @Override
    public void deleteAllInBatch() {
        appointmentRepo.deleteAllInBatch();
    }

    @Override
    @Deprecated
    public Appointment getOne(Long aLong) {
        return appointmentRepo.getOne(aLong);
    }

    @Override
    @Deprecated
    public Appointment getById(Long aLong) {
        return appointmentRepo.getById(aLong);
    }

    @Override
    public Appointment getReferenceById(Long aLong) {
        return appointmentRepo.getReferenceById(aLong);
    }

    @Override
    public <S extends Appointment> List<S> findAll(Example<S> example) {
        return appointmentRepo.findAll(example);
    }

    @Override
    public <S extends Appointment> List<S> findAll(Example<S> example, Sort sort) {
        return appointmentRepo.findAll(example, sort);
    }

    @Override
    public <S extends Appointment> List<S> saveAll(Iterable<S> entities) {
        return appointmentRepo.saveAll(entities);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepo.findAll();
    }

    @Override
    public List<Appointment> findAllById(Iterable<Long> longs) {
        return appointmentRepo.findAllById(longs);
    }

    @Override
    public <S extends Appointment> S save(S entity) {
        return appointmentRepo.save(entity);
    }

    @Override
    public Optional<Appointment> findById(Long aLong) {
        return appointmentRepo.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return appointmentRepo.existsById(aLong);
    }

    @Override
    public long count() {
        return appointmentRepo.count();
    }

    @Override
    public void deleteById(Long aLong) {
        appointmentRepo.deleteById(aLong);
    }

    @Override
    public void delete(Appointment entity) {
        appointmentRepo.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        appointmentRepo.deleteAllById(longs);
    }

    @Override
    public void deleteAll(Iterable<? extends Appointment> entities) {
        appointmentRepo.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        appointmentRepo.deleteAll();
    }

    @Override
    public List<Appointment> findAll(Sort sort) {
        return appointmentRepo.findAll(sort);
    }

    @Override
    public Page<Appointment> findAll(Pageable pageable) {
        return appointmentRepo.findAll(pageable);
    }

    @Override
    public <S extends Appointment> Optional<S> findOne(Example<S> example) {
        return appointmentRepo.findOne(example);
    }

    @Override
    public <S extends Appointment> Page<S> findAll(Example<S> example, Pageable pageable) {
        return appointmentRepo.findAll(example, pageable);
    }

    @Override
    public <S extends Appointment> long count(Example<S> example) {
        return appointmentRepo.count(example);
    }

    @Override
    public <S extends Appointment> boolean exists(Example<S> example) {
        return appointmentRepo.exists(example);
    }

    @Override
    public <S extends Appointment, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return appointmentRepo.findBy(example, queryFunction);
    }


    @Override
    public List<Appointment> findByPatientId(Long patientId) {
        return appointmentRepo.findByPatientId(patientId);
    }
}
