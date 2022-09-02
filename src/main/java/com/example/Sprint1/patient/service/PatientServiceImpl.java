package com.example.Sprint1.patient.service;

import java.util.List;
import java.util.Set;

import javax.validation.Validator;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Sprint1.doctor.domain.repository.DoctorRepository;
import com.example.Sprint1.patient.domain.model.Patient;
import com.example.Sprint1.patient.domain.respository.PatientRepository;
import com.example.Sprint1.patient.domain.service.PatientService;
import com.example.Sprint1.shared.exception.ResourceNotFoundException;
import com.example.Sprint1.shared.exception.ResourceValidationException;

@Service
public class PatientServiceImpl implements PatientService {

    private static final String ENTITY = "Patient";
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private Validator validator;

    @Override
    public List<Patient> getAllByDoctorId(Long doctorId) {
        var exitingDoctor = doctorRepository.findById(doctorId);

        if(exitingDoctor.isEmpty())
            throw new ResourceNotFoundException("Doctor", doctorId);

        return patientRepository.findByDoctorId(doctorId);
    }

    @Override
    public Patient createPatient(Long doctorId, Patient request) {
        Set<ConstraintViolation<Patient>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return doctorRepository.findById(doctorId).map(doctor -> {
            request.setDoctor(doctor);
            return patientRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException("Doctor", doctorId));
    }

    @Override
    public Patient updatePatient(Long patientId, Patient request) {
        Set<ConstraintViolation<Patient>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return patientRepository.findById(patientId).map(
                patientP -> patientRepository.save(
                       patientP.withName(request.getName())
                                .withAge(request.getAge())
                                .withGener(request.getGener())
                                .withBloodT(request.getBloodT()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, patientId));
    }

    @Override
    public ResponseEntity<?> deletePatient(Long patientId) {
        return patientRepository.findById(patientId).map(patientP -> {
            patientRepository.delete(patientP);
            return ResponseEntity.ok().build();
        })
        .orElseThrow(() -> new ResourceNotFoundException(ENTITY, patientId));
    }
    
}
