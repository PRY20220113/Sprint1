package com.example.Sprint1.patient.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Sprint1.patient.domain.model.Patient;

@Service
public interface PatientService {
    List<Patient> getAllByDoctorId(Long doctorId);

    Patient createPatient(Long doctorId, Patient patient);

    Patient updatePatient(Long patientId, Patient patient);

    ResponseEntity<?> deletePatient(Long patientId);
}
