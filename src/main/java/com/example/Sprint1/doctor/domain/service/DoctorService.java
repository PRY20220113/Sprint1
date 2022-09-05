package com.example.Sprint1.doctor.domain.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.Sprint1.doctor.domain.model.Doctor;
import com.example.Sprint1.doctor.domain.service.communication.RegisterDoctorRequest;
import com.example.Sprint1.security.domain.service.communication.AuthenticateRequest;


public interface DoctorService extends UserDetailsService {
    //List<Doctor> getAllDoctors();
    Doctor getByDoctorId(Long doctorId);
    //Doctor createDoctor(Doctor doctor);
    Doctor updateDoctor(Long doctorId, Doctor doctor);
    ResponseEntity<?> deleteDoctor(Long doctorId);

    ResponseEntity<?> authenticate(AuthenticateRequest request);
    ResponseEntity<?> register(RegisterDoctorRequest request);
}
