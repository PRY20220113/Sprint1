package com.example.Sprint1.patient.api;

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

import com.example.Sprint1.patient.domain.service.PatientService;
import com.example.Sprint1.patient.mapping.PatientMapper;
import com.example.Sprint1.patient.resource.PatientResource;
import com.example.Sprint1.patient.resource.SavePatientResource;

@RestController
@RequestMapping("/api/v1")
public class PatientController {
    private final PatientService patientService;

    private final PatientMapper mapper;

    public PatientController(PatientService patientService, PatientMapper mapper) {
        this.patientService = patientService;
        this.mapper = mapper;
    }

    @GetMapping("doctor/{doctorId}/patient")
    public List<PatientResource> GetTeachersByDirectorId(@PathVariable("doctorId") Long doctorId){
        return mapper.modelListToResource(patientService.getAllByDoctorId(doctorId));
    }

    @PostMapping("doctor/{doctorId}/patient")
    public PatientResource CreateTeacher(@PathVariable("doctorId")Long doctorId, @RequestBody SavePatientResource resource){
        return mapper.toResource(patientService.createPatient(doctorId, mapper.toModel(resource)));
    }

    @PutMapping("patient/{patientId}")
    public PatientResource UpdateTeacher(@PathVariable("patientId") Long patientId, @RequestBody SavePatientResource resource){
        return mapper.toResource(patientService.updatePatient(patientId, mapper.toModel(resource)));
    }

    @DeleteMapping("patient/{patientId}")
    public ResponseEntity<?> DeleteTeacher(@PathVariable("patientId") Long patientId){
        return patientService.deletePatient(patientId);
    }
}
