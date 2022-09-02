package com.example.Sprint1.doctor.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Sprint1.doctor.domain.model.Doctor;
import com.example.Sprint1.doctor.domain.repository.DoctorRepository;
import com.example.Sprint1.doctor.domain.service.DoctorService;
import com.example.Sprint1.shared.exception.ResourceNotFoundException;
import com.example.Sprint1.shared.exception.ResourceValidationException;

@Service
public class DoctorServiceImpl implements DoctorService {

    private static final String ENTITY = "Doctor";
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private Validator validator;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getByDoctorId(Long doctorId) {
        return doctorRepository.findById(doctorId)
        .orElseThrow( () -> new ResourceNotFoundException(ENTITY, doctorId));
    }

    @Override
    public Doctor createDoctor(Doctor request) {
        Set<ConstraintViolation<Doctor>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        var existingEmail = doctorRepository.FindByEmail(request.getEmail());
        
        if(existingEmail != null) {
            throw new ResourceValidationException("email is already taken");
        }

        return doctorRepository.save(request);
    }

    @Override
    public Doctor updateDoctor(Long doctorId, Doctor request) {
        Set<ConstraintViolation<Doctor>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return doctorRepository.findById(doctorId).map( data ->
                doctorRepository.save(
                            data.withName(request.getName())
                                .withSurname(request.getSurname())
                                .withEmail(request.getEmail())
                                .withDni(request.getDni())
                                .withSfeesNum(request.getSfeesNum())
                                .withPhone(request.getPhone())
                                .withPassword(request.getPassword())
        )).orElseThrow(() -> new ResourceNotFoundException(ENTITY, doctorId));
    }

    @Override
    public ResponseEntity<?> deleteDoctor(Long doctorId) {
        return doctorRepository.findById(doctorId).map(data -> {
            doctorRepository.delete(data);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, doctorId));
    }
    
}
