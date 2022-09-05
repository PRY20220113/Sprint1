package com.example.Sprint1.patient.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Sprint1.patient.domain.service.PatientService;
import com.example.Sprint1.patient.domain.service.communication.RegisterPatientRequest;
import com.example.Sprint1.patient.mapping.PatientMapper;
import com.example.Sprint1.patient.resource.PatientResource;
import com.example.Sprint1.patient.resource.SavePatientResource;
import com.example.Sprint1.security.domain.service.communication.AuthenticateRequest;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientMapper mapper;

    @GetMapping("{patientId}")
    @PreAuthorize("hasRole('PATIENT')")
    public PatientResource GetPatientById(@PathVariable("patientId") Long patientId) {
        return mapper.toResource(patientService.getByPatientId(patientId));
    }

    @PutMapping("{patientId}")
    @PreAuthorize("hasRole('PATIENT')")
    public PatientResource UpdatePatient(@PathVariable("patientId") Long patientId, @RequestBody SavePatientResource request) {
        return mapper.toResource(patientService.updatePatient(patientId, mapper.toModel(request)));
    }

    @DeleteMapping("{patientId}")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<?> DeletePatient(@PathVariable("patientId") Long patientId) {
        return patientService.deletePatient(patientId);
    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterPatientRequest request) {
        return patientService.register(request);
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticateRequest request) {
        return patientService.authenticate(request);
    }
}
