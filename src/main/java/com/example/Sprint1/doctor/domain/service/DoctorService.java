package com.example.Sprint1.doctor.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Sprint1.doctor.domain.model.Doctor;

@Service
public interface DoctorService {
    List<Doctor> getAllDoctors();
    Doctor getByDoctorId(Long doctorId);
    Doctor createDoctor(Doctor doctor);
    Doctor updateDoctor(Long doctorId, Doctor doctor);
    ResponseEntity<?> deleteDoctor(Long doctorId);
}
