package com.example.Sprint1.patient.domain.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.Sprint1.patient.domain.model.Patient;
import com.example.Sprint1.patient.domain.service.communication.RegisterPatientRequest;
import com.example.Sprint1.security.domain.service.communication.AuthenticateRequest;


public interface PatientService extends UserDetailsService {
    //List<Patient> getAllByDoctorId(Long patientId);
    //Patient createPatient(Long patientId, Patient patient);
    Patient getByPatientId(Long patientId);
    Patient updatePatient(Long patientId, Patient request);
    ResponseEntity<?> deletePatient(Long patientId);

    ResponseEntity<?> authenticate(AuthenticateRequest request);
    ResponseEntity<?> register(RegisterPatientRequest request);
}
