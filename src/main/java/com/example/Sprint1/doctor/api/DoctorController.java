package com.example.Sprint1.doctor.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Sprint1.doctor.domain.service.DoctorService;
import com.example.Sprint1.doctor.mapping.DoctorMapper;
import com.example.Sprint1.doctor.resource.DoctorResource;
import com.example.Sprint1.doctor.resource.SaveDoctorResource;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    private final DoctorMapper mapper;

    public DoctorController(DoctorService doctorService, DoctorMapper mapper) {
        this.doctorService = doctorService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DoctorResource> GetAllDoctors() {
        return mapper.modelListToResource(doctorService.getAllDoctors());
    }

    @GetMapping("{doctorId}")
    public DoctorResource GetDoctorById(@PathVariable("doctorId") Long doctorId) {
        return mapper.toResource(doctorService.getByDoctorId(doctorId));
    }

    @PostMapping
    public DoctorResource CreateDoctor(@RequestBody SaveDoctorResource request) {
        return mapper.toResource(doctorService.createDoctor(mapper.toModel(request)));
    }

    @PutMapping("{doctorId}")
    public DoctorResource UpdateDoctor(@PathVariable("doctorId") Long doctorId, @RequestBody SaveDoctorResource request) {
        return mapper.toResource(doctorService.updateDoctor(doctorId, mapper.toModel(request)));
    }

    @DeleteMapping("{doctorId}")
    public ResponseEntity<?> DeleteDoctor(@PathVariable("doctorId") Long doctorId) {
        return doctorService.deleteDoctor(doctorId);
    }
}
