package com.example.Sprint1.doctor.controller;

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

import com.example.Sprint1.doctor.domain.service.DoctorService;
import com.example.Sprint1.doctor.domain.service.communication.RegisterDoctorRequest;
import com.example.Sprint1.doctor.mapping.DoctorMapper;
import com.example.Sprint1.doctor.resource.DoctorResource;
import com.example.Sprint1.doctor.resource.SaveDoctorResource;
import com.example.Sprint1.security.domain.service.communication.AuthenticateRequest;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorMapper mapper;

    //@GetMapping
    //public List<DoctorResource> GetAllDoctors() {
    //    return mapper.modelListToResource(doctorService.getAllDoctors());
    //}

    @GetMapping("{doctorId}")
    @PreAuthorize("hasRole('DOCTOR')")
    public DoctorResource GetDoctorById(@PathVariable("doctorId") Long doctorId) {
        return mapper.toResource(doctorService.getByDoctorId(doctorId));
    }

    @PutMapping("{doctorId}")
    @PreAuthorize("hasRole('DOCTOR')")
    public DoctorResource UpdateDoctor(@PathVariable("doctorId") Long doctorId, @RequestBody SaveDoctorResource request) {
        return mapper.toResource(doctorService.updateDoctor(doctorId, mapper.toModel(request)));
    }

    @DeleteMapping("{doctorId}")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<?> DeleteDoctor(@PathVariable("doctorId") Long doctorId) {
        return doctorService.deleteDoctor(doctorId);
    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDoctorRequest request) {
        return doctorService.register(request);
    }

    @PostMapping("/auth/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticateRequest request) {
        return doctorService.authenticate(request);
    }
}
